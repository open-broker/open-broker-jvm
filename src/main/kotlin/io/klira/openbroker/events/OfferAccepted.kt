package io.klira.openbroker.events

import io.klira.openbroker.model.BankAccount
import io.klira.openbroker.model.Reference
import io.klira.openbroker.requireMin

data class OfferAccepted(
    val providerOfferReference: Reference,
    val bankAccount: BankAccount? = null,
    val requestedCredit: Int? = null
): OpenBrokerEvent
{
    init {
        requestedCredit.requireMin(1, "requestedCredit")
    }
}