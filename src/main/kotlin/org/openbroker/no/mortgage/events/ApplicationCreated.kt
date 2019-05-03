package org.openbroker.no.mortgage.events

import org.openbroker.common.model.DataProtectionContext
import org.openbroker.common.model.InformationContext
import org.openbroker.common.model.Reference
import org.openbroker.no.mortgage.model.Application


/**
 * An event indicating the creation of an application
 * for a mortgage
 */
data class ApplicationCreated(
    val application: Application,
    override val brokerReference: Reference,
    override val dataProtectionContext: DataProtectionContext
): MortgageEvent, InformationContext