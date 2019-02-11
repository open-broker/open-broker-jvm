package org.openbroker.mortgage.model

data class ExistingLoan(
    val loanAmount: Int,
    val monthlyCost: Int,
    val existingLoanType: ExistingLoanType
)
