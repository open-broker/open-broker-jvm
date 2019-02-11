package org.openbroker.common.loancosts

import java.math.BigDecimal
import java.math.MathContext

object Annuity: LoanType {

    override fun totalCost(
        loanAmount: Int,
        nominalAnnualInterestRate: Double,
        termFee: Int,
        termMonths: Int
    ): BigDecimal {
        val monthlyCost: BigDecimal = monthlyCost(loanAmount, nominalAnnualInterestRate, termMonths)
        val monthlyTermCost = BigDecimal(termFee)
        return (monthlyCost + monthlyTermCost).multiply(BigDecimal(termMonths), MathContext.DECIMAL128)
    }

    override fun apr(
        loanAmount: Int,
        nominalAnnualInterestRate: Double,
        termFee: Int,
        termMonths: Int
    ): BigDecimal {
        val totalCost: BigDecimal =
            totalCost(loanAmount, nominalAnnualInterestRate, termFee, termMonths)
        val monthlyPayment: Double = totalCost.divide(BigDecimal(termMonths), MathContext.DECIMAL128).toDouble()
        return effectiveInterestRate(loanAmount, monthlyPayment, termMonths)
    }

    override fun monthlyCost(
        loanAmount: Int,
        nominalAnnualInterestRate: Double,
        termMonths: Int
    ): BigDecimal {
        val monthlyRate: Double = nominalAnnualInterestRate / 12.0
        val monthlyDividend: Double = monthlyRate * Math.pow(1 + monthlyRate, termMonths.toDouble())
        val monthlyDivider: Double = Math.pow(1 + monthlyRate, termMonths.toDouble()) - 1
        val fixedMonthlyPayment: Double = loanAmount * (monthlyDividend / monthlyDivider)
        return BigDecimal(fixedMonthlyPayment)
    }

    private const val ACCURACY_THRESHOLD = 0.000001

    private tailrec fun effectiveInterestRate(
        loanAmount: Int,
        monthlyPayment: Double,
        paymentTerms: Int,
        estimatedApr: Double = 0.5,
        leverage: Double = 1.0
    ): BigDecimal {
        val estimationLow: Double = estimatedApr - leverage / 5.0
        val estimationHigh: Double = estimatedApr + leverage / 5.0
        val y1: Double = computeDelta(estimationLow, loanAmount, monthlyPayment, paymentTerms)
        val y2: Double = computeDelta(estimationHigh, loanAmount, monthlyPayment, paymentTerms)
        if(y1 < ACCURACY_THRESHOLD)
            return BigDecimal(estimatedApr)
        val newAprEstimation: Double = if(y1 < y2) estimationLow else estimationHigh
        return effectiveInterestRate(
            loanAmount,
            monthlyPayment,
            paymentTerms,
            newAprEstimation,
            y1 / loanAmount
        )
    }

    private tailrec fun computeDelta(aprGuess: Double, loanAmount: Int, monthlyPayment: Double, paymentTerms: Int, paid: Double = 0.0): Double {
        if(paymentTerms == 0)
            return Math.abs(loanAmount - paid)
        val divider: Double = Math.pow(1 + aprGuess, paymentTerms / 12.0)
        val paymentCurrentTerm: Double = monthlyPayment * (1 / divider)
        return computeDelta(
            aprGuess,
            loanAmount,
            monthlyPayment,
            paymentTerms - 1,
            paid + paymentCurrentTerm
        )
    }
}