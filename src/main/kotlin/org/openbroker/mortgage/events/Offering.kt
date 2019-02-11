package org.openbroker.mortgage.events

import org.openbroker.mortgage.model.Offer
import org.openbroker.common.model.Reference

/**
 * An offer for a mortgage
 */
data class Offering constructor(
    override val brokerReference: Reference,
    val offer: Offer
): MortgageEvent