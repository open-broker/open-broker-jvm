package org.openbroker.se.mortgage.events

import org.openbroker.se.mortgage.model.Offer
import org.openbroker.common.model.Reference

/**
 * An offer for a mortgage
 */
data class Offering constructor(
    override val brokerReference: Reference,
    val offerId: Reference? = null,
    val offer: Offer
): MortgageEvent