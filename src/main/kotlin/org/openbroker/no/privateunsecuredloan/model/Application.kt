package org.openbroker.no.privateunsecuredloan.model

import org.openbroker.common.requireLessThanOrEqual
import org.openbroker.common.requireMin

data class Application @JvmOverloads constructor(
    /**
     * The primary applicant to the loan
     */
    val applicant: Applicant,

    /**
     * A secondary applicant for the loan, if any
     */
    val coApplicant: Applicant? = null,

    /**
     * Information about existing loans relevant to the applicant
     */
    val existingLoans: List<ExistingLoan> = emptyList(),

    /**
     * Custom, vendor-specific extensions to the schema
     */
    val extensions: Map<String, Any>? = emptyMap(),

    /**
     * The amount that the customer wishes to borrow
     */
    val loanAmount: Int,

    /**
     * Purpose of the loan
     */
    val loanPurpose: LoanPurpose = LoanPurpose.OTHER,

    /**
     * The amount being refinanced, must be less than the loanAmount
     */
    val refinanceAmount: Int = 0,

    /**
     * The number 1-month terms that the applicant desires to pay off the loan over
     */
    val termMonths: Int
    ) {
    init {
        loanAmount.requireMin(1, "loanAmount")
        refinanceAmount.requireMin(0, "refinanceAmount")
        termMonths.requireMin(1, "termMonths")
        refinanceAmount.requireLessThanOrEqual(loanAmount, "loanAmount", "refinanceAmount")
    }
}