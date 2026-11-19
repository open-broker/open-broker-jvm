package org.openbroker.no.privateunsecuredloan.model

/**
 * The kind of offer.
 *
 * Defaults to [STANDARD] when omitted. Must not be null.
 */
enum class OfferType {
    /**
     * An offer based on the amount the customer applied for
     */
    STANDARD,

    /**
     * An alternative refinance offer, based on the debt in the debt register
     * instead of the amount applied for. The creditor sends it when the
     * register shows more loans than the customer stated in the application.
     * It comes in the same response as a [STANDARD] offer, and the customer
     * can accept only one of them.
     */
    DUAL,

    /**
     * One of several alternatives from the creditor, for example the same
     * amount with different terms. The customer can accept only one of them.
     */
    MULTIPLE
}
