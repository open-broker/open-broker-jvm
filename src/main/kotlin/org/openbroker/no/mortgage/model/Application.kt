package org.openbroker.no.mortgage.model

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


    val refinancingProperty: RefinancingProperty,

    /**
     * Custom, vendor-specific extensions to the schema
     */
    val extensions: Map<String, Any>? = emptyMap(),

    /**
     * The amount that the customer wishes to borrow, including
     * refinancing
     */
    val loanAmount: Int,

    /**
     * Purpose of the loan
     */
    val loanPurpose: LoanPurpose = LoanPurpose.OTHER,

    /**
     * The number of year terms that the applicant desires to pay off the loan over
     */
    val termYears: Int,
    /**
     * An optional human-readable comment about the application, presenting
     * extra information about the application, such as cause to today's situation.
     */
    val comment: String? = null
) {
    init {
        loanAmount.requireMin(1, "loanAmount")
        termYears.requireMin(1, "termYears")
        val refinanceAmount: Int = refinanceAmount()
        refinanceAmount.requireLessThanOrEqual(loanAmount, "loanAmount", "refinanceAmount")
    }

    /**
     * The amount the customer wishes to refinance of existing loans
     */
    fun refinanceAmount(): Int = existingLoans.sumBy { it.refinanceAmount }
}