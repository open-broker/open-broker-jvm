package org.openbroker.no.privateunsecuredloan.events

import org.openbroker.common.model.BankAccount
import org.openbroker.common.model.Reference
import org.openbroker.common.requireMin

data class OfferAccepted @JvmOverloads constructor(
    override val brokerReference: Reference,
    val bankAccount: BankAccount? = null,
    val requestedCredit: Int? = null
): PrivateUnsecuredLoanEvent
{
    init {
        requestedCredit.requireMin(1, "requestedCredit")
    }
}