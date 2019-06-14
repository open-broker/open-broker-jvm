package org.openbroker.common.model

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.openbroker.common.loancosts.StraightLine
import java.math.BigDecimal

private const val ACCEPTABLE_ERROR: Double = 0.6

class StraightLineMonthlyCostTest {

    @Test
    fun testComputeMonthlyCostForSmallLoanNoTermFee() {
        val totalCost: BigDecimal = StraightLine.monthlyCost(
            loanAmount = 10_000,
            nominalAnnualInterestRate = 0.05,
            termMonths = 6
        )

        Assertions.assertEquals(1691.5, totalCost.toDouble(), ACCEPTABLE_ERROR)
    }

    @Test
    fun testComputeMonthlyCostForBigLoanNoTermFee() {
        val totalCost: BigDecimal = StraightLine.monthlyCost(
            loanAmount = 200_000,
            nominalAnnualInterestRate = 0.067,
            termMonths = 3
        )

        Assertions.assertEquals(67_411.33, totalCost.toDouble(), ACCEPTABLE_ERROR)
    }
}