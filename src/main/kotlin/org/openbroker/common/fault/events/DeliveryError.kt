package org.openbroker.common.fault.events

import org.openbroker.common.model.Reference

/**
 * An event indicating that request was not delivered successfully
 */

data class DeliveryError constructor(
    val eventType: String? = null,
    val eventID: String? = null,
    val message: String? = null,
    val shouldRetry: Boolean? = null,
    override val brokerReference: Reference = Reference("","stub.stub")
) : ErrorEvent