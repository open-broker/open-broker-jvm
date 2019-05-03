package org.openbroker.no.mortgage.model

/**
 * Decides in which manner the loan is amortized
 */
enum class AmortizationType {
    /**
     * The monthly sum that is payed by the customer is fixed, and the amount
     * that is amortized is gradually increased since the the amount to cover
     * the interest rate each month decreases which each amortisation
     */
    ANNUITY,

    /**
     * The amount paid for amortization each month is fixed, while the interest
     * payment will be lower over time, meaning that the total monthly cost for
     * the loan will decrease over time
     */
    STRAIGHT_LINE
}