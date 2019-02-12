package org.openbroker.se.mortgage.model

enum class ExistingLoanType {
    /**
     * A loan for a car or similar vehicle
     */
    CAR_LOAN,
    /**
     * A revolving credit, other than a credit-card.
     */
    CHECK_CREDIT,
    /**
     * A credit-card
     */
    CREDIT_CARD,
    /**
     * A student loan
     */
    STUDENT_LOAN,
    /**
     * An unsecured loan, not falling into any of the other categories, for example a consumer loan.
     */
    UNSECURED_LOAN,
    /**
     * Any other debt not falling into any of the other categories.
     */
    OTHER;
}