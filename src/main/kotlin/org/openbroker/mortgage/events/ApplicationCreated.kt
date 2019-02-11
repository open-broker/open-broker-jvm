package org.openbroker.mortgage.events

import org.openbroker.common.model.DataProtectionContext
import org.openbroker.common.model.Reference
import org.openbroker.mortgage.model.Application

/**
 * An event indicating the creation of an application
 * for a mortgage
 */
data class ApplicationCreated(
    override val brokerReference: Reference,
    val application: Application,
    val dataProtectionContext: DataProtectionContext
): MortgageEvent