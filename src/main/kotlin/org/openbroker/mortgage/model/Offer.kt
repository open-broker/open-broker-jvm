package org.openbroker.mortgage.model

import org.openbroker.common.requireMin

data class Offer(
    val effectiveInterestRate: String,
    val nominalInterestRate: String,
    val monthlyCost: Int? = null,
    val arrangementFee: Int,
    val termFee: Int,
    val invoiceFee: Int,
    val termYears: Int? = null,
    val expires: String? = null
) {
    init {
        require(effectiveInterestRate.matches(interestRateRegex)) {
            "Bad format of effective interest rate: '$effectiveInterestRate'"
        }

        require(nominalInterestRate.matches(interestRateRegex)) {
            "Bad format of nominal interest rate: '$nominalInterestRate'"
        }

        arrangementFee.requireMin(0, "arrangementFee")
        termFee.requireMin(0, "termFee")
        invoiceFee.requireMin(0, "invoiceFee")

        expires?.let {
            require(it.matches(iso8601dateRegex)) {
                "Invalid expires argument: '$it'"
            }
        }
    }

    companion object {
        val interestRateRegex = Regex("^[0-9]+(.[0-9]+)?$")
        val iso8601dateRegex = Regex("\\d{4}-\\d{2}-\\d{2}")
    }
}