package org.openbroker.privateunsecuredloan.events

import org.openbroker.privateunsecuredloan.model.DelayReason
import org.openbroker.common.model.Reference

/**
 * An event indicating that processing of the application is delayed
 */
data class DelayedProcessing(override val brokerReference: Reference, val delayReason: DelayReason): PrivateUnsecuredLoanEvent