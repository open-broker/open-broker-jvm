package org.openbroker.no.mortgage.model

import org.openbroker.common.model.AmortizationType
import org.openbroker.common.requireMatchRegex
import org.openbroker.common.requireMin

data class Offer(
    val effectiveInterestRate: String,
    val nominalInterestRate: String,
    val offeredCredit: Int,
    val monthlyCost: Int? = null,
    val mustRefinance: Int,
    val arrangementFee: Int,
    val termFee: Int,
    val invoiceFee: Int,
    val termYears: Int,
    val amortizationType: AmortizationType
) {
    init {
        val interestRateRegex = Regex("^[0-9]+(.[0-9]+)?$")

        effectiveInterestRate.requireMatchRegex(interestRateRegex, "effectiveInterestRate")
        nominalInterestRate.requireMatchRegex(interestRateRegex, "nominalInterestRate")
        offeredCredit.requireMin(1, "offeredCredit")
        mustRefinance.requireMin(0, "mustRefinance")
        arrangementFee.requireMin(0, "arrangementFee")
        termFee.requireMin(0, "termFee")
        invoiceFee.requireMin(0, "invoiceFee")
        termYears.requireMin(1, "termYears")
    }
}