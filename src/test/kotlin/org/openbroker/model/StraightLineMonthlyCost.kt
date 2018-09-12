package org.openbroker.model

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.openbroker.loancosts.StraightLine
import java.math.BigDecimal

private const val ACCEPTABLE_ERROR: Double = 0.5

class StraightLineMonthlyCost {

    @Test
    fun testComputeMonthlyCostForSmallLoanNoTermFee() {
        val totalCost: BigDecimal = StraightLine.monthlyCost(
            loanAmount = 10_000,
            nominalAnnualInterestRate = 0.05,
            termMonths = 24
        )

        Assertions.assertEquals(459.0, totalCost.toDouble(), ACCEPTABLE_ERROR)
    }

    @Test
    fun testComputeMonthlyCostForBigLoanNoTermFee() {
        val totalCost: BigDecimal = StraightLine.monthlyCost(
            loanAmount = 200_000,
            nominalAnnualInterestRate = 0.067,
            termMonths = 96
        )

        Assertions.assertEquals(3_200.0, totalCost.toDouble(), ACCEPTABLE_ERROR)
    }
}