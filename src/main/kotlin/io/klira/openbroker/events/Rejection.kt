package io.klira.openbroker.events

import io.klira.openbroker.model.Reference

/**
 *  An event indicating that the application was rejected
 */
data class Rejection(val brokerReference: Reference): OpenBrokerEvent