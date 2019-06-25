package org.openbroker.no.mortgage.events

import org.openbroker.common.model.Reference

/**
 * An event that may be sent by the broker to indicate that
 * the offer has been rejected
 */
data class OfferRejected(
    override val brokerReference: Reference,
    val offerId: Reference? = null
): MortgageEvent