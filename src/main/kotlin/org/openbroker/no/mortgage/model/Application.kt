package org.openbroker.no.mortgage.model

import org.openbroker.common.model.issuerRegex
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
     * The amount that the customer wishes to borrow
     */
    val loanAmount: Int,

    /**
     * Purpose of the loan
     */
    val loanPurpose: LoanPurpose = LoanPurpose.OTHER,

    /**
     * The amount being refinanced, must be less than or equal to the loanAmount
     */
    val refinanceAmount: Int = 0,

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
        refinanceAmount.requireMin(0, "refinanceAmount")
        termYears.requireMin(1, "termYears")
        require(refinanceAmount <= loanAmount) {
            "refinanceAmount ($refinanceAmount) may not be greater than loanAmount ($loanAmount)."
        }
        extensions?.keys?.forEach { key ->
            require(key.matches(issuerRegex)) {
                "Key for extension is not a valid format: '$key'"
            }
        }
    }
}