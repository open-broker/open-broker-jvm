package org.openbroker.no.privateunsecuredloan.events

import org.openbroker.common.model.Reference
import org.openbroker.common.requireNotEqual
import org.openbroker.no.privateunsecuredloan.model.LoanPartType

/**
 * An event indicating that the bank has received a signed contract from the customer
 *
 * There is one event for the offer's own loan, with no `type`, and one for each
 * [LoanPartType.COMBO] or [LoanPartType.COMBO_TOP_UP] part of the accepted offer.
 * They can come in any order.
 */
data class ContractSigned @JvmOverloads constructor(
    override val brokerReference: Reference,
    val type: LoanPartType? = null
): PrivateUnsecuredLoanEvent {
    init {
        type.requireNotEqual(LoanPartType.TOP_UP, "type")
    }
}
