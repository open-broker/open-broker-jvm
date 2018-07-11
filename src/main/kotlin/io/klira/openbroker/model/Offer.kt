package io.klira.openbroker.model

import io.klira.openbroker.requireMin

data class Offer(
    val effectiveInterestRate: String,
    val nominalInterestRate: String,
    val minOfferedCredit: Int,
    val offeredCredit: Int,
    val maxOfferedCredit: Int,
    val monthlyCost: Int,
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