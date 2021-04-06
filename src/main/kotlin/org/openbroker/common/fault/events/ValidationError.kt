package org.openbroker.common.fault.events

import org.openbroker.common.model.Reference

/**
 * An event indicating that request is failed with a validation error
 */

data class ValidationError constructor(
    val eventType: String? = null,
    val eventID: String? = null,
    val originalMessage: String? = null,
    val message: String? = null,
    val origin: String? = null,
    override val brokerReference: Reference = Reference("","stub.stub")
) : ErrorEvent