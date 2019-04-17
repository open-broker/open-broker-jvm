package org.openbroker.no.mortgage.events

import org.openbroker.common.model.Reference
import org.openbroker.common.requireMin
import org.openbroker.se.mortgage.events.MortgageEvent

data class OfferAccepted @JvmOverloads constructor(
    override val brokerReference: Reference,
    val bankAccount: String? = null,
    val requestedCredit: Int? = null
): MortgageEvent
{
    init {
        requestedCredit.requireMin(1, "requestedCredit")
    }
}