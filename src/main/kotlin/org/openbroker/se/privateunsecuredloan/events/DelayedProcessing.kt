package org.openbroker.se.privateunsecuredloan.events

import org.openbroker.se.privateunsecuredloan.model.DelayReason
import org.openbroker.common.model.Reference

/**
 * An event indicating that processing of the application is delayed
 */
data class DelayedProcessing(override val brokerReference: Reference, val delayReason: DelayReason): PrivateUnsecuredLoanEvent