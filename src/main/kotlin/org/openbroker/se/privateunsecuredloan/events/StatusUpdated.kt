package org.openbroker.se.privateunsecuredloan.events

import org.openbroker.common.model.Reference
import org.openbroker.se.privateunsecuredloan.model.Status

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
): PrivateUnsecuredLoanEvent