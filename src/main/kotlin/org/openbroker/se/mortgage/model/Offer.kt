package org.openbroker.se.mortgage.model

import org.openbroker.common.requireMatchRegex
import org.openbroker.common.requireMin

data class Offer(
    val effectiveInterestRate: String? = null,
    val nominalInterestRate: String,
    val monthlyCost: Int? = null,
    val arrangementFee: Int,
    val termFee: Int,
    val invoiceFee: Int,
    val termYears: Int? = null,
    val expires: String? = null,
    val comment: String? = null
) {
    init {
        effectiveInterestRate?.requireMatchRegex(interestRateRegex, "effectiveInterestRate")
        nominalInterestRate.requireMatchRegex(interestRateRegex, "nominalInterestRate")
        arrangementFee.requireMin(0, "arrangementFee")
        termFee.requireMin(0, "termFee")
        invoiceFee.requireMin(0, "invoiceFee")
        expires?.requireMatchRegex(iso8601dateRegex, "expires")
    }

    companion object {
        val interestRateRegex = Regex("^[0-9]+(.[0-9]+)?$")
        val iso8601dateRegex = Regex("\\d{4}-\\d{2}-\\d{2}")
    }
}