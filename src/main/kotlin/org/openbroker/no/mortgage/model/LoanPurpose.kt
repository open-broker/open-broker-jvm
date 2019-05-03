package org.openbroker.no.mortgage.model

enum class LoanPurpose {
    /**
     * Refinancing of private unsecured loans with the existing mortgage
     */
    REFINANCE_CONSUMER_LOANS,
    /**
     * Refinancing of an existing mortgage
     */
    REFINANCE_MORTGAGE,
    /**
     * Home remodelling or renovation
     */
    HOME_REMODELLING,
    /**
     * Purpose not fitting the above categories
     */
    OTHER
}