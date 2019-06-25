package org.openbroker.se.mortgage.events

import org.openbroker.common.model.Reference

/**
 * An event that may be sent by the broker to indicate that
 * the offer has been rejected by the customer
 */
data class OfferRejected(
    override val brokerReference: Reference,
    val offerId: Reference? = null
): MortgageEvent