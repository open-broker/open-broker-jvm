package io.klira.openbroker.events

import io.klira.openbroker.model.Reference
import io.klira.openbroker.requireMin

/**
 * An event indicating that a private unsecured loan has been disbursed
 */
data class Disbursed(
    override val brokerReference: Reference,

    /**
     * The total amount disbursed to the customer of the loan being brokered
     */
    val amountDisbursed: Int,

    /**
     * The amount the lender considers the broker to have
     * brokered. For example, the amount disbursed exclusive
     * of loans refinanced within the lender.
     */
    val amountBrokered: Int
): OpenBrokerEvent {
    init {
        amountDisbursed.requireMin(1, "amountDisbursed")
    	amountBrokered.requireMin(0, "amountBrokered")

        require(amountBrokered <= amountDisbursed) {
            "amountBrokered ($amountBrokered) must be equal to or less than amountDisbursed ($amountDisbursed)"
        }
    }
}