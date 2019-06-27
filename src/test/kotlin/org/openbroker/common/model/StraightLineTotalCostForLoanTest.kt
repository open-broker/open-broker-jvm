package org.openbroker.common.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.openbroker.common.loancosts.StraightLine
import java.math.BigDecimal

private const val ACCEPTABLE_ERROR: Double = 0.5

class StraightLineTotalCostForLoanTest {

    @Test
    fun testComputeTotalCostForSmallLoanNoTermFee() {
        val totalCost: BigDecimal = StraightLine.totalCost(
            loanAmount = 10_000,
            nominalAnnualInterestRate = 0.05,
            administrationFee = 0,
            termFee = 0,
            termMonths = 24
        )

        assertEquals(10_521.0, totalCost.toDouble(), ACCEPTABLE_ERROR)
    }

    @Test
    fun testComputeTotalCostForBigLoanNoTermFee() {
        val totalCost: BigDecimal = StraightLine.totalCost(
            loanAmount = 200_000,
            nominalAnnualInterestRate = 0.067,
            administrationFee = 0,
            termFee = 0,
            termMonths = 96
        )

        assertEquals(254_158.0, totalCost.toDouble(), ACCEPTABLE_ERROR)
    }


    @Test
    fun testComputeTotalCostForSmallLoanWithTermFee() {
        val totalCost: BigDecimal = StraightLine.totalCost(
            loanAmount = 10_000,
            nominalAnnualInterestRate = 0.05,
            administrationFee = 0,
            termFee = 20,
            termMonths = 24
        )

        assertEquals(11_001.0, totalCost.toDouble(), ACCEPTABLE_ERROR)
    }

    @Test
    fun testComputeTotalCostForBigLoanWithTermFee() {
        val totalCost: BigDecimal = StraightLine.totalCost(
            loanAmount = 200_000,
            nominalAnnualInterestRate = 0.067,
            administrationFee = 0,
            termFee = 60,
            termMonths = 96
        )

        assertEquals(259_918.0, totalCost.toDouble(), ACCEPTABLE_ERROR)
    }
}