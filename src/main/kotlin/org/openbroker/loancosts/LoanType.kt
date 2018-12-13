package org.openbroker.loancosts

import java.math.BigDecimal

interface LoanType {
    /**
     * Compute APR (annual percentage rate), sometimes referred to as
     * "effective interest rate"
     * @param loanAmount the amount loaned
     * @param nominalAnnualInterestRate the nominal interest rate on annual basis
     * @param termFee any fee that is paid each month, not including amortization
     * and interest rate payments
     * @param termMonths for how many months the loan is payed off over
     */
    fun apr(
        loanAmount: Int,
        nominalAnnualInterestRate: Double,
        termFee: Int,
        termMonths: Int
    ): BigDecimal

    /**
     * Compute the total cost of a annuity loan, given the
     * @param loanAmount the amount loaned
     * @param nominalAnnualInterestRate the nominal interest rate on annual basis
     * @param termFee any fee that is paid each month, not including amortization
     * and interest rate payments
     * @param termMonths for how many months the loan is payed off over
     *
     * @return the total cost of the loan
     */
    fun totalCost(
        loanAmount: Int,
        nominalAnnualInterestRate: Double,
        termFee: Int,
        termMonths: Int
    ): BigDecimal


    /**
     * Compute the monthly costs for a loan from amortization and interest
     * rate, but not including term fees.
     *
     * @param loanAmount the amount loaned
     * @param nominalAnnualInterestRate the nominal interest rate on annual basis
     * @param termMonths for how many months the loan is payed off over
     *
     * @return the monthly cost of the loan, excluding term fees
     */
    fun monthlyCost(
        loanAmount: Int,
        nominalAnnualInterestRate: Double,
        termMonths: Int
    ): BigDecimal
}