package org.openbroker.mortgage.events

import org.openbroker.common.model.Reference

/**
 * An interface for all Mortgage events
 */
interface MortgageEvent {
    /**
     * A reference-id used by the broker to identify the application
     */
    val brokerReference: Reference
}