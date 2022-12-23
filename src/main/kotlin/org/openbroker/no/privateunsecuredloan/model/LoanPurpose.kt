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
     * To finance a downpayment for a loan
     */
    HOME_DOWNPAYMENT,
    /**
     * To finance health-care cost
     */
    HEALTHCARE_EXPENSES,
    /**
     * To finance costs relating to a divorce
     */
    DIVORCE_PROCEEDINGS,
    /**
     * To finance getting a driver's licence
     */
    DRIVERS_LICENSE,
    /**
     * To refinance or pay off overdue loans
     */
    DEBT_COLLECTION,
    /**
     * General consumption
     */
    CONSUMPTION,
    /**
     * To finance moving to a different location, like different town
     */
    RELOCATION,
    /**
     * To finance an investment
     */
    INVESTMENT,
    /**
     * To finance education of some kind
     */
    EDUCATION,
    /**
     * To finance a new interior, like new kitchen, furniture
     */
    INTERIOR,
    /**
     * To finance a wedding
     */
    WEDDING,
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