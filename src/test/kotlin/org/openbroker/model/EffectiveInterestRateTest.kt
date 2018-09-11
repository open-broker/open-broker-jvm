package org.openbroker.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class EffectiveInterestRateTest {

    @Test
    fun testComputeEffectiveInterestRate() {
        // 9.82%
        //assertEquals(0.0982, effectiveInterestRate(10_000, 0.05, 20.0, 24, AmortizationType.ANNUITY), 0.00001)
    }

    @Test
    fun testComputeTotalCostForLoan() {
        val totalCost: BigDecimal = totalCostOfLoan(
            loanAmount = 10_000,
            nominalAnnualInterestRate = 0.05,
            termFee = 0,
            termMonths = 24
        )

        assertEquals(10529.0, totalCost.toDouble(), 0.001)
    }
}