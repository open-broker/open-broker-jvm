package org.openbroker.events

import org.openbroker.model.Reference

/**
 *  An event indicating that the application was rejected
 */
data class Rejection(override val brokerReference: Reference): OpenBrokerEvent