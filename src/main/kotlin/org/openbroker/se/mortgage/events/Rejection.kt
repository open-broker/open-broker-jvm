package org.openbroker.se.mortgage.events

import org.openbroker.common.model.Reference

/**
 *  An event indicating that the application was rejected by the service provider
 */
data class ApplicationRejection(
    override val brokerReference: Reference,
    val rejectReason: String? = null
): MortgageEvent