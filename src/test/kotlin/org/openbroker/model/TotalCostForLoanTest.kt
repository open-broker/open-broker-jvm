package org.openbroker.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigDecimal

private const val ACCEPTABLE_ERROR: Double = 0.2

class TotalCostForLoanTest {

    @Test
    fun testComputeEffectiveInterestRate() {
        // 9.82%
        //assertEquals(0.0982, effectiveInterestRate(10_000, 0.05, 20.0, 24, AmortizationType.ANNUITY), 0.00001)
    }

    @Test
    fun testComputeTotalCostForSmallLoanNoTermFee() {
        val totalCost: BigDecimal = totalCostOfLoan(
            loanAmount = 10_000,
            nominalAnnualInterestRate = 0.05,
            termFee = 0,
            termMonths = 24
        )

        assertEquals(10_529.0, totalCost.toDouble(), ACCEPTABLE_ERROR)
    }

    @Test
    fun testComputeTotalCostForBigLoanNoTermFee() {
        val totalCost: BigDecimal = totalCostOfLoan(
            loanAmount = 200_000,
            nominalAnnualInterestRate = 0.067,
            termFee = 0,
            termMonths = 96
        )

        assertEquals(258_910.0, totalCost.toDouble(), ACCEPTABLE_ERROR)
    }


    @Test
    fun testComputeTotalCostForSmallLoanWithTermFee() {
        val totalCost: BigDecimal = totalCostOfLoan(
            loanAmount = 10_000,
            nominalAnnualInterestRate = 0.05,
            termFee = 20,
            termMonths = 24
        )

        assertEquals(11_009.0, totalCost.toDouble(), ACCEPTABLE_ERROR)
    }

    @Test
    fun testComputeTotalCostForBigLoanWithTermFee() {
        val totalCost: BigDecimal = totalCostOfLoan(
            loanAmount = 200_000,
            nominalAnnualInterestRate = 0.067,
            termFee = 60,
            termMonths = 96
        )

        assertEquals(264_670.0, totalCost.toDouble(), ACCEPTABLE_ERROR)
    }
}