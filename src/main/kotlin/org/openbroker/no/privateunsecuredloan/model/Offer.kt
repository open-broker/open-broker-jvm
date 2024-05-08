package org.openbroker.no.privateunsecuredloan.model

import org.openbroker.common.model.AmortizationType
import org.openbroker.common.requireInRange
import org.openbroker.common.requireLessThanOrEqual
import org.openbroker.common.requireMatchRegex
import org.openbroker.common.requireMin

data class Offer(
    val effectiveInterestRate: String? = null,
    val nominalInterestRate: String? = null,
    val minOfferedCredit: Int? = null,
    val offeredCredit: Int,
    val maxOfferedCredit: Int? = null,
    val monthlyCost: Int? = null,
    val mustRefinance: Int? = null,
    val arrangementFee: Int,
    val termFee: Int,
    val invoiceFee: Int,
    val termMonths: Int? = null,
    val amortizationType: AmortizationType? = null
) {
    init {
        val interestRateRegex = Regex("^0\\.([1-9]\\d?|\\d[1-9])(\\d+)?\$")
        effectiveInterestRate?.requireMatchRegex(interestRateRegex, "effectiveInterestRate")
        nominalInterestRate?.requireMatchRegex(interestRateRegex, "nominalInterestRate")
        minOfferedCredit?.requireMin(1, "minOfferedCredit")
        offeredCredit.requireMin(1, "offeredCredit")
        maxOfferedCredit?.requireMin(1, "maxOfferedCredit")
        if (minOfferedCredit != null && maxOfferedCredit != null) {
            minOfferedCredit.requireLessThanOrEqual(maxOfferedCredit, "maxOfferedCredit", "minOfferedCredit")
            offeredCredit.requireInRange(minOfferedCredit, maxOfferedCredit, "offeredCredit")
        }
        mustRefinance?.requireMin(0, "mustRefinance")
        arrangementFee.requireMin(0, "arrangementFee")
        termFee.requireMin(0, "termFee")
        invoiceFee.requireMin(0, "invoiceFee")
        termMonths?.requireMin(1, "termMonths")
    }
}