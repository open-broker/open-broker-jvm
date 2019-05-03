package org.openbroker.no.mortgage.events

import org.openbroker.common.model.Reference

/**
 *  An event indicating that the application was rejected
 */
data class ApplicationRejection(
    override val brokerReference: Reference,
    val rejectReason: String? = null
): MortgageEvent