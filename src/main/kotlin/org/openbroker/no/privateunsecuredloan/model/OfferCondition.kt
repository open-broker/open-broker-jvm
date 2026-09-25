package org.openbroker.no.privateunsecuredloan.model

/**
 * A condition attached to the offer. The offer can be accepted while the
 * condition is still open, but the condition must be fulfilled before the
 * offer can be disbursed.
 *
 * Null or omitted when the offer has no condition.
 */
enum class OfferCondition {
    /**
     * A co-applicant must be added to the application before the offer can be disbursed
     */
    CO_APPLICANT_NEEDED
}
