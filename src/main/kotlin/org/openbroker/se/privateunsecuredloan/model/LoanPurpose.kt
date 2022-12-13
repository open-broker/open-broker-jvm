package org.openbroker.se.privateunsecuredloan.model

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
     * To finance getting a driver's licence
     */
    DRIVERS_LICENSE,
    /**
     * To refinance or pay off overdue loans
     */
    DEBT_COLLECTION,
    /**
     * To finance moving to a different location, like different town
     */
    RELOCATION,
    /**
     * General consumption
     */
    CONSUMPTION,
    /**
     * To finance a new interior, like new kitchen, furniture
     */
    INTERIOR,
    /**
     * To finance a wedding
     */
    WEDDING,
    /**
     * Home remodelling or renovation
     */
    HOME_REMODELLING,
    /**
     * To finance an investment
     */
    INVESTMENT,
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