package org.openbroker.no.privateunsecuredloan.events

import org.openbroker.common.model.Reference
import org.openbroker.no.privateunsecuredloan.model.OfferRejectedReason

/**
 * An event that may be sent by the broker or the provider to indicate that
 * the offer has been rejected
 */
data class OfferRejected(
    override val brokerReference: Reference,
    val offerId: Reference? = null,
    val reason: OfferRejectedReason? = null
): PrivateUnsecuredLoanEvent
