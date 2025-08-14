package org.openbroker.no.privateunsecuredloan.model

import org.openbroker.common.requireMatchRegex
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
        val interestRateRgx = Regex("^(0\\.[0-9]+|1\\.0+)$")
        nominalInterestRate?.requireMatchRegex(interestRateRgx, "nominalInterestRate")
        remainingTerms?.requireMin(1, "remainingTerms")
        monthlyPayment.requireMin(1, "monthlyPayment")
    }
}