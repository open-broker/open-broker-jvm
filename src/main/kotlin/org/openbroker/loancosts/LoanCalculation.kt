package org.openbroker.loancosts

import org.openbroker.model.AmortizationType
import java.math.BigDecimal
import java.math.MathContext


/**
 * Compute APR (annual percentage rate), sometimes referred to effective
 * interest rate
 */
fun effectiveInterestRate(
    loanAmount: Int,
    nominalAnnualInterestRate: Double,
    termFee: Int,
    termMonths: Int,
    loanType: AmortizationType
): BigDecimal =
    if(loanType == AmortizationType.STRAIGHT_LINE) {
//        val interestAndAmortization: BigDecimal = totalInterestRateCostsStraightLine(loanAmount, nominalAnnualInterestRate, termMonths)
//        val loanCost = BigDecimal(loanAmount)
//        val termFees = BigDecimal(termFee * termMonths)
//        val totalCost = interestAndAmortization + loanCost + termFees
        BigDecimal.ZERO // TODO
    }
    else {
        TODO()
        /*val totalCost: BigDecimal = totalCostOfLoan(loanAmount, nominalAnnualInterestRate, termFee, termMonths)
        val monthlyPayment: Double = totalCost.divide(BigDecimal(termMonths), MathContext.DECIMAL128).toDouble()
        effectiveInterestRateAnnuity(loanAmount, monthlyPayment, termMonths)*/
    }
