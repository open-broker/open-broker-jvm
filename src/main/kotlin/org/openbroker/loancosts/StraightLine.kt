package org.openbroker.loancosts

import java.math.BigDecimal
import java.math.MathContext

object StraightLine: LoanType {
    override fun apr(loanAmount: Int, nominalAnnualInterestRate: Double, termFee: Int, termMonths: Int): BigDecimal {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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
        val netCost: BigDecimal = cost - BigDecimal(loanAmount)
        return netCost.divide(BigDecimal(termMonths), MathContext.DECIMAL128)
    }
}

private fun totalInterestRateCosts(
    loanAmount: Int,
    nominalAnnualInterestRate: Double,
    termMonths: Int
): BigDecimal {
    val principal = BigDecimal(loanAmount)
    val monthlyRate = BigDecimal(nominalAnnualInterestRate/12.0)
    val rate = monthlyRate.divide(BigDecimal(2), MathContext.DECIMAL128)
    val x = principal.multiply(rate, MathContext.DECIMAL128)
    return BigDecimal(termMonths + 1).multiply(x, MathContext.DECIMAL128)
}