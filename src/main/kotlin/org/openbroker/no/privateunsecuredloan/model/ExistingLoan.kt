package org.openbroker.no.privateunsecuredloan.model

import org.openbroker.common.requireMin

data class ExistingLoan(
    val loanAmount: Int,
    val nominalInterestRate: String? = null,
    val remainingTerms: Int? = null,
    val monthlyPayment: Int,
    val refinanceAmount: Int? = null,
    val existingLoanType: ExistingLoanType,
    val responsibility: Responsibility,
    val lender: String
) {
    init {
        loanAmount.requireMin(1, "loanAmount")
        remainingTerms?.requireMin(1, "remainingTerms")
        monthlyPayment.requireMin(1, "monthlyPayment")
    }
}