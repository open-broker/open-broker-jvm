package org.openbroker.se.mortgage.events

import org.openbroker.common.model.Reference
import org.openbroker.common.requireMin

/**
 * An event indicating that a mortgage has been disbursed
 */
data class Disbursed(
    override val brokerReference: Reference,

    /**
     * The total amount disbursed to the customer of the loan being brokered
     */
    val amountDisbursed: Int,
    /**
     *  The date for which the loan was disbursed, formatted
     *  as a ISO-8601 date, in the extended format YYYY-MM-DD
     */
    val date: String,

    /**
     * An id for the offer that has been disbursed,
     * if the offer has an identifier
     */
    val offerId: Reference? = null
): MortgageEvent
{
    init {
        amountDisbursed.requireMin(1, "amountDisbursed")
        require(date.matches(Regex("\\d{4}-\\d{2}-\\d{2}"))) {
            "Invalid date argument: '$date'"
        }
    }
}