package org.openbroker.no.privateunsecuredloan.model

import org.openbroker.common.model.AmortizationType
import org.openbroker.common.requireInRange
import org.openbroker.common.requireLessThanOrEqual
import org.openbroker.common.requireMatchRegex
import org.openbroker.common.requireMin

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
        effectiveInterestRate.requireMatchRegex(interestRateRegex, "effectiveInterestRate")
        nominalInterestRate.requireMatchRegex(interestRateRegex, "nominalInterestRate")

        minOfferedCredit.requireMin(1, "minOfferedCredit")
        offeredCredit.requireMin(1, "offeredCredit")
        maxOfferedCredit.requireMin(1, "maxOfferedCredit")
        minOfferedCredit.requireLessThanOrEqual(maxOfferedCredit, "maxOfferedCredit", "minOfferedCredit")
        offeredCredit.requireInRange(minOfferedCredit, maxOfferedCredit, "offeredCredit")
        mustRefinance.requireMin(0, "mustRefinance")
        arrangementFee.requireMin(0, "arrangementFee")
        termFee.requireMin(0, "termFee")
        invoiceFee.requireMin(0, "invoiceFee")
        termMonths.requireMin(1, "termMonths")
    }
}