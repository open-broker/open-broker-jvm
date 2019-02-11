package org.openbroker.privateunsecuredloan.events

import org.openbroker.common.model.Reference

/**
 * An interface for all Private Unsecured Loan events
 */
interface PrivateUnsecuredLoanEvent {
    /**
     * A reference-id used by the broker to identify the application
     */
    val brokerReference: Reference
}