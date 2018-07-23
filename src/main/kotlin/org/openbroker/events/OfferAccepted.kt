package org.openbroker.events

import org.openbroker.model.BankAccount
import org.openbroker.model.Reference
import org.openbroker.requireMin

data class OfferAccepted(
    override val brokerReference: Reference,
    val bankAccount: BankAccount? = null,
    val requestedCredit: Int? = null
): OpenBrokerEvent
{
    init {
        requestedCredit.requireMin(1, "requestedCredit")
    }
}