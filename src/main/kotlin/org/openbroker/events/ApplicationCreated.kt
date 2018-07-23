package org.openbroker.events

import org.openbroker.model.Application
import org.openbroker.model.DataProtectionContext
import org.openbroker.model.Reference

/**
 * An event indicating the creation of an application
 * for a private unsecured loan
 */
data class ApplicationCreated(
    val application: Application,
    override val brokerReference: Reference,
    val dataProtectionContext: DataProtectionContext
): OpenBrokerEvent