package org.openbroker.no.privateunsecuredloan.model

/**
 * The kind of loan part, and whether its amounts are included in the offer
 * amounts.
 */
enum class LoanPartType {
    /**
     * The new money added to a loan the customer already has at this
     * creditor. It is included in the offer amounts. `topUpAmount` is the new
     * money. `offeredCredit`, rates and costs, when sent, are for the new
     * money alone.
     */
    TOP_UP,

    /**
     * A separate new loan, in its own creditor case, accepted together with
     * the offer. It is not included in the offer amounts. `topUpAmount` is
     * null.
     */
    COMBO,

    /**
     * A separate loan, in its own creditor case, that tops up a loan the
     * customer already has at this creditor. It is accepted together with the
     * offer and is not included in the offer amounts. `offeredCredit` is the
     * whole loan after the top-up, and `topUpAmount` is the new money in it.
     */
    COMBO_TOP_UP
}
