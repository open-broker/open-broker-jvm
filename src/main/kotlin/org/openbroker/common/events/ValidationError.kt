package org.openbroker.common.events

import org.openbroker.common.model.Reference

/**
 * An event indicating that request is failed with a validation error
 */

data class ValidationError constructor(
    override val brokerReference: Reference, //todo could be null
    val eventType: String? = null,
    val eventID: String? = null,
    val originalMessage: String? = null,
    val message: String? = null,
    val origin: String? = null
) : ErrorEvent