package org.openbroker.no.privateunsecuredloan.model

/**
 * The reason for rejecting an offer.
 */
enum class OfferRejectedReason {
    /**
     * The customer has submitted a new application, which replaces this one
     */
    NEW_APPLICATION_SUBMITTED,

    /**
     * The application was cancelled by the bank
     */
    CANCELLED_BY_BANK
}
