package org.openbroker.no.privateunsecuredloan.events

import org.openbroker.common.model.DataProtectionContext
import org.openbroker.common.model.InformationContext
import org.openbroker.common.model.Reference
import org.openbroker.no.privateunsecuredloan.model.Application


/**
 * An event indicating the creation of an application
 * for a private unsecured loan
 */
data class ApplicationCreated(
    val application: Application,
    override val brokerReference: Reference,
    override val dataProtectionContext: DataProtectionContext
): org.openbroker.no.privateunsecuredloan.events.PrivateUnsecuredLoanEvent, InformationContext