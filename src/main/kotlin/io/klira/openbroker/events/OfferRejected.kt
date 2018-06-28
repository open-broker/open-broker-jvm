package io.klira.openbroker.events

import io.klira.openbroker.model.Reference

/**
 * An event that may be sent by the broker to indicate that
 * the offer has been rejected
 */
data class OfferRejected(val providerOfferReference: Reference): OpenBrokerEvent