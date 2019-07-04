package org.openbroker.se.privateunsecuredloan.events

import org.openbroker.common.model.Reference
import org.openbroker.common.requireMin

/**
 * An event indicating that a private unsecured loan has been disbursed
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
    val amountBrokered: Int
): PrivateUnsecuredLoanEvent {
    init {
        amountDisbursed.requireMin(1, "amountDisbursed")
    	amountBrokered.requireMin(0, "amountBrokered")

        require(amountBrokered <= amountDisbursed) {
            "amountBrokered ($amountBrokered) must be equal to or less than amountDisbursed ($amountDisbursed)"
        }
    }
}