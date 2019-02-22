package org.openbroker.no.privateunsecuredloan.model

import org.openbroker.common.requireMin

data class ExistingLoan(
    val loanAmount: Int,
    val monthlyPayment: Int,
    val refinanceAmount: Int? = null,
    val existingLoanType: ExistingLoanType,
    val responsibility: Responsibility,
    val lender: String
) {
    init {
        loanAmount.requireMin(1, "loanAmount")
        monthlyPayment.requireMin(1, "monthlyPayment")
    }
}