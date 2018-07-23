package org.openbroker.events

import org.openbroker.model.DelayReason
import org.openbroker.model.Reference

/**
 * An event indicating that processing of the application is delayed
 */
data class DelayedProcessing(override val brokerReference: Reference, val delayReason: DelayReason): OpenBrokerEvent