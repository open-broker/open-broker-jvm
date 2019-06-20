package org.openbroker.no.privateunsecuredloan.model

enum class LoanPurpose {
    /**
     * Refinancing existing debt
     */
    REFINANCE,
    /**
     * Home remodelling or renovation
     */
    HOME_REMODELLING,
    /**
     * To finance health-care cost
     */
    HEALTHCARE_EXPENSES,
    /**
     * To finance an investment
     */
    INVESTMENT,
    /**
     * To finance education of some kind
     */
    EDUCATION,
    /**
     * To finance a vacation or other travel expenses
     */
    TRAVEL,
    /**
     * To finance the purchase of a car or similar
     */
    CAR,
    /**
     * Purpose not fitting the above categories
     */
    OTHER
}