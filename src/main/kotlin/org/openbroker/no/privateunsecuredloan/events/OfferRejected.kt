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
    val reason: OfferRejectedReason,
    /**
     * Free-text comment or decline reason from the creditor. Values are not
     * standardised. Usually set for [OfferRejectedReason.CANCELLED_BY_BANK]
     * and null for [OfferRejectedReason.NEW_APPLICATION_SUBMITTED].
     */
    val reasonDetails: String? = null
): PrivateUnsecuredLoanEvent
