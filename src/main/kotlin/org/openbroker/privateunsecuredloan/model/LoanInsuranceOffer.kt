package org.openbroker.privateunsecuredloan.model

import org.openbroker.common.requireMin

data class LoanInsuranceOffer @JvmOverloads constructor(
    val insuredAmount: Int,
    val monthlyPremium: Int,
    val descriptiveText: String? = null
) {
    init {
        insuredAmount.requireMin(1, "insuredAmount")
        monthlyPremium.requireMin(1, "monthlyPremium")
    }
}