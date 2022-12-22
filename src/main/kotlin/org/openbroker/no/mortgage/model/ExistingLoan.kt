package org.openbroker.no.mortgage.model

import org.openbroker.common.requireMin
import org.openbroker.common.requireNotEmpty

data class ExistingLoan(
    val loanAmount: Int,
    val monthlyPayment: Int,
    val refinanceAmount: Int = 0,
    val existingLoanType: ExistingLoanType,
    val responsibility: Responsibility,
    val lender: String,
    val defaulted: Boolean
) {
    init {
        loanAmount.requireMin(1, "loanAmount")
        monthlyPayment.requireMin(1, "monthlyPayment")
        lender.requireNotEmpty("lender")
    }
}