package org.openbroker.no.mortgage.events

import org.openbroker.common.model.Reference

/**
 * An event containing information on the loan process
 */

data class Message constructor(
    override val brokerReference: Reference,
    val message: String,
    val requiresAction: Boolean
) : MortgageEvent