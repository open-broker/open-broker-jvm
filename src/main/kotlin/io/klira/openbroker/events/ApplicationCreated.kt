package io.klira.openbroker.events

import io.klira.openbroker.model.Application
import io.klira.openbroker.model.DataProtectionContext
import io.klira.openbroker.model.Reference

/**
 * An event indicating the creation of an application
 * for a private unsecured loan
 */
data class ApplicationCreated(
    val application: Application,
    val brokerReference: Reference,
    val dataProtectionContext: DataProtectionContext
): OpenBrokerEvent