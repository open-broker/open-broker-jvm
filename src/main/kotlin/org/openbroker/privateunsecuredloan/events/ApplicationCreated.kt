package org.openbroker.privateunsecuredloan.events

import org.openbroker.common.model.DataProtectionContext
import org.openbroker.common.model.Reference

/**
 * An event indicating the creation of an application
 * for a private unsecured loan
 */
data class ApplicationCreated(
    val application: org.openbroker.privateunsecuredloan.model.Application,
    override val brokerReference: Reference,
    val dataProtectionContext: DataProtectionContext
): PrivateUnsecuredLoanEvent