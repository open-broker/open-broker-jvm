package org.openbroker.se.mortgage.events

import org.openbroker.common.model.Reference

data class Message(
    override val brokerReference: Reference,
    val message: String,
    val actionRequired: Boolean
): MortgageEvent