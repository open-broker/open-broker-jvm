package org.openbroker.common.loancosts

import java.math.BigDecimal
import java.math.MathContext

object StraightLine: LoanType {
    override fun effectiveInterestRate(loanAmount: Int, nominalAnnualInterestRate: Double, termFee: Int, termMonths: Int): BigDecimal {
        // Save this for another day
        // https://www.fi.se/contentassets/46eeed14d88842c29d6104b9492c6742/fffs9733.pdf
        TODO("not implemented")
    }

    override fun totalCost(
        loanAmount: Int,
        nominalAnnualInterestRate: Double,
        termFee: Int,
        termMonths: Int
    ): BigDecimal {
        val principal = BigDecimal(loanAmount)
        val monthlyRate = BigDecimal(nominalAnnualInterestRate/12.0)
        val rate = monthlyRate.divide(BigDecimal(2), MathContext.DECIMAL128)
        val x = principal.multiply(rate, MathContext.DECIMAL128)
        val termFeeCosts = BigDecimal(termFee * termMonths)
        return BigDecimal(loanAmount)
            .plus(termFeeCosts)
            .plus(BigDecimal(termMonths + 1).multiply(x, MathContext.DECIMAL128))
    }

    override fun monthlyCost(loanAmount: Int, nominalAnnualInterestRate: Double, termMonths: Int): BigDecimal {
        val cost: BigDecimal = totalCost(loanAmount, nominalAnnualInterestRate, 0, termMonths)
        return cost.divide(BigDecimal(termMonths), MathContext.DECIMAL128)
    }
}