package org.openbroker.no.privateunsecuredloan.model

/**
 * The kind of credit product an offer represents.
 *
 * Defaults to [UNSECURED_LOAN] when omitted. Must not be null.
 */
enum class CreditorProduct {
    /**
     * An unsecured personal loan paid out as a lump sum and amortized over a
     * fixed term
     */
    UNSECURED_LOAN,

    /**
     * A revolving credit account accessed through a credit card
     */
    CREDIT_CARD,

    /**
     * A revolving line of credit the customer can draw from up to a credit limit
     */
    CREDIT_LINE
}
