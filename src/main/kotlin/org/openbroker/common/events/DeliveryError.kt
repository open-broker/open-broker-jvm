package org.openbroker.common.events

import org.openbroker.common.model.Reference

/**
 * An event indicating that request was not delivered successfully
 */

data class DeliveryError constructor(
    override val brokerReference: Reference, //todo could be null
    val eventType: String? = null,
    val eventID: String? = null,
    val message: String? = null,
    val shouldRetry: Boolean? = null
) : ErrorEvent