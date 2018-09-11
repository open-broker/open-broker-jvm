package org.openbroker.model

import org.openbroker.requireMin
import java.math.BigDecimal
import java.math.MathContext

data class Offer(
    val effectiveInterestRate: String,
    val nominalInterestRate: String,
    val minOfferedCredit: Int,
    val offeredCredit: Int,
    val maxOfferedCredit: Int,
    val monthlyCost: Int? = null,
    val mustRefinance: Int,
    val arrangementFee: Int,
    val termFee: Int,
    val invoiceFee: Int,
    val termMonths: Int,
    val amortizationType: AmortizationType
) {
    init {
        val interestRateRegex = Regex("^[0-9]+(.[0-9]+)?$")

        require(effectiveInterestRate.matches(interestRateRegex)) {
            "Bad format of effective interest rate: '$effectiveInterestRate'"
        }

        require(nominalInterestRate.matches(interestRateRegex)) {
            "Bad format of nominal interest rate: '$nominalInterestRate'"
        }

        minOfferedCredit.requireMin(1, "minOfferedCredit")
        offeredCredit.requireMin(1, "offeredCredit")
        maxOfferedCredit.requireMin(1, "maxOfferedCredit")

        require(minOfferedCredit <= maxOfferedCredit) { "minOfferedCredit cannot be greater than maxOfferedCredit" }
        require(offeredCredit in minOfferedCredit..maxOfferedCredit) {
            "offeredCredit must be within the range of minOfferedCredit and maxOfferedCredit"
        }

        mustRefinance.requireMin(0, "mustRefinance")
        arrangementFee.requireMin(0, "arrangementFee")
        termFee.requireMin(0, "termFee")
        invoiceFee.requireMin(0, "invoiceFee")
        termMonths.requireMin(1, "termMonths")
    }
}

/**
 * Compute the effective annual interest rate
 */
fun effectiveAnnualInterestRateXX(offeredCredit: Int, nominalAnnualInterestRate: Double, termMonths: Int, termFee: Int): String {
    require(nominalAnnualInterestRate < 1.0) { "Bad percent value: $nominalAnnualInterestRate = ${nominalAnnualInterestRate*100}%" }
    val annualInterestRateCost: Double = offeredCredit * nominalAnnualInterestRate
    val years: Double = termMonths / 12.0
    val totalInterestRateCost: Double = annualInterestRateCost * years
    val totalTermFeeCost: Int = termFee * termMonths
    val effectiveCost: Double = totalInterestRateCost + totalTermFeeCost
    val effectiveInterestRate: Double = effectiveCost / offeredCredit
    val effectiveAnnualInterestRate: Double = effectiveInterestRate / years
    return effectiveAnnualInterestRate.toString()
}

/**
 * Compute the total cost of a loan, given the
 * @param loanAmount the amount loaned
 * @param nominalAnnualInterestRate the nominal interest rate on annual basis
 * @param termFee any fee that is paid each month, not including amortization
 * and interest rate payments
 * @param termMonths for how many months the loan is payed off over
 *
 * @return the total cost of the loan
 */
fun totalCostOfLoan(
    loanAmount: Int,
    nominalAnnualInterestRate: Double,
    termFee: Int,
    termMonths: Int
): BigDecimal {
    //val loan = BigDecimal(loanAmount)
    //val totalInterestCost: BigDecimal = totalInterestRateCosts(loanAmount, nominalAnnualInterestRate, termMonths)
    //val totalTermFeesCost = BigDecimal(termFee * termMonths)
    //return loan + totalInterestCost + totalTermFeesCost

    //return BigDecimal(loanAmount) + totalInterestRateCosts(loanAmount, nominalAnnualInterestRate, termMonths)
    val monthlyRate: Double = nominalAnnualInterestRate / 12.0
    val fixedMonthlyPayment: Double = loanAmount * ((monthlyRate * Math.pow(1 + monthlyRate, termMonths.toDouble())) / (Math.pow(1 +
        monthlyRate, termMonths.toDouble()) - 1))
    return BigDecimal(fixedMonthlyPayment)
}

fun totalInterestRateCosts(
    loanAmount: Int,
    nominalAnnualInterestRate: Double,
    termMonths: Int,
    paidInterest: BigDecimal = BigDecimal.ZERO,
    amortization: BigDecimal = BigDecimal.ZERO
): BigDecimal {
    //val monthlyRate: BigDecimal = BigDecimal(nominalAnnualInterestRate).divide(BigDecimal(12), MathContext.DECIMAL128)
    val monthlyRate: Double = nominalAnnualInterestRate / 12.0
    val fixedMonthlyPayment: Double = loanAmount * ((monthlyRate * Math.pow(1 + monthlyRate, termMonths.toDouble())) / (Math.pow(1 +
        monthlyRate, termMonths.toDouble()) - 1))
    return BigDecimal.ZERO
}

fun totalInterestRateCostsStraightLine(
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

/**
 * Compute APR (annual percentage rate), sometimes referred to effective
 * interest rate
 */
fun effectiveInterestRate(
    loanAmount: Int,
    nominalInterestRate: Double,
    termFee: Double,
    termMonths: Int,
    loanType: AmortizationType
): BigDecimal =
    if(loanType == AmortizationType.STRAIGHT_LINE)
        throw UnsupportedOperationException()
    else {
        val pvOfAnnuity: Double = pvOfAnnuity(nominalInterestRate, termMonths)
        val payment: Double = pvOfAnnuity * (nominalInterestRate / 12.0) / (1 - Math.pow(1 + nominalInterestRate, - termMonths.toDouble()))
        BigDecimal.ZERO//((payment * loanAmount * termMonths) / loanAmount.toDouble())
    }
        //(Math.pow(1 + nominalInterestRate, 12.0) - 1.0) * 12
        //((amount + nominalInterestRate * amount) + totalTermFee(termFee, termMonths)) / amount

fun effectiveInterestRate(
    loanAmount: Int,
    totalLoanCost: Int,
    termMonths: Int,
    y: Double = 1.0,
    iteration: Int = 1
): BigDecimal {
    return BigDecimal.ZERO
}

private tailrec fun pvOfAnnuity(nominalInterestRate: Double, termMonths: Int, paid: Double = 0.0): Double {
    if(termMonths == 0)
        return paid
    val pForPeriod: Double = (1 - Math.pow(1 + nominalInterestRate, - termMonths.toDouble())) / nominalInterestRate
    return pvOfAnnuity(nominalInterestRate, termMonths - 1, pForPeriod)//paid + (1 - Math.pow(1 + nominalInterestRate, - termMonths.toDouble())) /
    // nominalInterestRate
}

private fun totalTermFee(termFee: Double, termMonths: Int): Double =
    termFee * termMonths


//private fun interestFromFees(termFee: Double, termMonths: Int, amount: Int): Double =
//    pctFees(termFee, termMonths, amount) / (termMonths / 12.0f)