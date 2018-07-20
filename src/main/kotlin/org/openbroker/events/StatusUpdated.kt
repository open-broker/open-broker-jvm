package org.openbroker.events

import io.klira.openbroker.model.Reference
import io.klira.openbroker.model.Status

/**
 * An event indicating an update to the status of a loan being brokered
 */
data class StatusUpdated(
    /**
     * A reference-id used by the broker
     */
    override val brokerReference: Reference,

    /**
     * The new status of the application
     */
    val status: Status
): OpenBrokerEvent