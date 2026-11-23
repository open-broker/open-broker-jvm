package org.openbroker.no.privateunsecuredloan.events

import com.fasterxml.jackson.annotation.JsonInclude
import org.openbroker.common.model.Reference
import org.openbroker.common.requireLessThanOrEqual
import org.openbroker.common.requireMin
import org.openbroker.common.requireNotEqual
import org.openbroker.no.privateunsecuredloan.model.LoanPartType

/**
 * An event indicating that a private unsecured loan has been disbursed
 *
 * There is one event for the offer's own loan, with no `type`, and one for each
 * [LoanPartType.COMBO] or [LoanPartType.COMBO_TOP_UP] part of the accepted offer.
 * They can come in any order. The amounts are for that loan alone. `offerId` is
 * the id of the offer in every event; the loan is identified by `type`.
 */
data class Disbursed @JvmOverloads constructor(
    override val brokerReference: Reference,
    @field:JsonInclude(JsonInclude.Include.NON_NULL) val offerId: Reference? = null,

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
    @field:JsonInclude(JsonInclude.Include.NON_NULL) val type: LoanPartType? = null
): PrivateUnsecuredLoanEvent {
    init {
        amountDisbursed.requireMin(1, "amountDisbursed")
        amountBrokered.requireMin(0, "amountBrokered")
        amountBrokered.requireLessThanOrEqual(amountDisbursed, "amountDisbursed", "amountBrokered")
        type.requireNotEqual(LoanPartType.TOP_UP, "type")
    }
}