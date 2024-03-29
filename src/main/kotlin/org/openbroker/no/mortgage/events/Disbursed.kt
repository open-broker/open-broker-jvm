package org.openbroker.no.mortgage.events

import org.openbroker.common.model.Reference
import org.openbroker.common.requireLessThanOrEqual
import org.openbroker.common.requireMatchRegex
import org.openbroker.common.requireMin

/**
 * An event indicating that a mortgage has been disbursed
 */
data class Disbursed(
    override val brokerReference: Reference,
    val offerId: Reference? = null,

    /**
     * The total amount disbursed to the customer of the loan being brokered
     */
    val amountDisbursed: Int,

    /**
     * The amount the lender considers the broker to have
     * brokered. For example, the amount disbursed exclusive
     * of loans refinanced within the lender.
     */
    val amountBrokered: Int,
    /**
     *  The date for which the loan was disbursed, formatted
     *  as a ISO-8601 date, in the extended format YYYY-MM-DD
     */
    val date: String
): MortgageEvent {
    init {
        amountDisbursed.requireMin(1, "amountDisbursed")
        amountBrokered.requireMin(0, "amountBrokered")
        amountBrokered.requireLessThanOrEqual(amountDisbursed, "amountDisbursed", "amountBrokered")
        date.requireMatchRegex(Regex("\\d{4}-\\d{2}-\\d{2}"), "date")
    }
}