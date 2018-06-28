package io.klira.openbroker.events

import io.klira.openbroker.model.DelayReason
import io.klira.openbroker.model.Reference

/**
 * An event indicating that processing of the application is delayed
 */
data class DelayedProcessing(val brokerReference: Reference, val delayReason: DelayReason): OpenBrokerEvent