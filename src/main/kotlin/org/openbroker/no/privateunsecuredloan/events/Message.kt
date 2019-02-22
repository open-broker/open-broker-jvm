package org.openbroker.no.privateunsecuredloan.events

import org.openbroker.common.model.Reference

/**
 * An event containing information on the loan process
 */

data class Message @JvmOverloads constructor(
    override val brokerReference: Reference,
    val message: String,
    val requiresAction: Boolean
) : PrivateUnsecuredLoanEvent