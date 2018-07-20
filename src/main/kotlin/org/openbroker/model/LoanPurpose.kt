package org.openbroker.model

enum class LoanPurpose {
    /**
     * To finance the purchase of a car or similar
     */
    CAR,
    /**
     * To finance costs relating to a divorce
     */
    DIVORCE_PROCEEDINGS,
    /**
     * To finance education of some kind
     */
    EDUCATION,
    /**
     * To finance health-care costs
     */
    HEALTHCARE_EXPENSES,
    /**
     * To finance a downpayment for a loan
     */
    HOME_DOWNPAYMENT,
    /**
     * Home remodelling or renovation
     */
    HOME_REMODELLING,
    /**
     * Refinancing existing debt
     */
    REFINANCE,
    /**
     * To finance a vacation or other travel expenses
     */
    TRAVEL,
    /**
     * Purpose not fitting the above categories
     */
    OTHER
}