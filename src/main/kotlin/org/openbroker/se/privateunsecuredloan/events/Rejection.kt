package org.openbroker.se.privateunsecuredloan.events

import org.openbroker.common.model.Reference

/**
 *  An event indicating that the application was rejected
 */
data class Rejection(override val brokerReference: Reference): PrivateUnsecuredLoanEvent