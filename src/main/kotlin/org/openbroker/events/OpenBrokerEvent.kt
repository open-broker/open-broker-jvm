package org.openbroker.events

import org.openbroker.model.Reference

/**
 * An interface for all Open Broker events
 */
interface OpenBrokerEvent {
    /**
     * A reference-id used by the broker to identify the application
     */
    val brokerReference: Reference
}