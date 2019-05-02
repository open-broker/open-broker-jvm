package org.openbroker.no.mortgage.events

import org.openbroker.common.model.Reference
import org.openbroker.no.mortgage.model.Offer

/**
 * An offer for a loan
 */
data class Offering constructor(
    override val brokerReference: Reference,
    val offer: Offer
): MortgageEvent