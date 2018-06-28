package io.klira.openbroker.model

import io.klira.openbroker.requireMin

data class LoanInsuranceOffer(
    val insuredAmount: Int,
    val monthlyPremium: Int,
    val descriptiveText: String? = null
) {
    init {
        insuredAmount.requireMin(1, "insuredAmount")
        monthlyPremium.requireMin(1, "monthlyPremium")
    }
}