package org.openbroker.no.privateunsecuredloan.model

/**
 * Tells if this is the first or a later version of the offer.
 *
 * Defaults to [NEW] when omitted. Must not be null.
 */
enum class OfferStatus {
    /**
     * First version of the offer
     */
    NEW,

    /**
     * A later version of the offer, for example when the conditions changed
     * after the offer was accepted
     */
    REVISED
}
