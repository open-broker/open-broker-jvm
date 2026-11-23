package org.openbroker.no.privateunsecuredloan.model

import com.fasterxml.jackson.annotation.JsonInclude
import org.openbroker.common.requireMin

data class LoanInsuranceOffer @JvmOverloads constructor(
    val insuredAmount: Int,
    val monthlyPremium: Int,
    @field:JsonInclude(JsonInclude.Include.NON_NULL) val descriptiveText: String? = null
) {
    init {
        insuredAmount.requireMin(1, "insuredAmount")
        monthlyPremium.requireMin(1, "monthlyPremium")
    }
}