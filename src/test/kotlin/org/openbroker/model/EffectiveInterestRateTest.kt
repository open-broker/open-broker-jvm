package org.openbroker.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class EffectiveInterestRateTest {

    @Test
    fun testComputeEffectiveInterestRate() {
        // 5.1%
        assertEquals(0.051, effectiveInterestRate(1_000, 0.01, 2, 5, AmortizationType.ANNUITY).toDouble(), 0.0001)

        // 9.82%
        assertEquals(0.0982, effectiveInterestRate(10_000, 0.05, 20, 24, AmortizationType.ANNUITY).toDouble(), 0.0001)

        // 27.97%
        assertEquals(0.2797, effectiveInterestRate(200_000, 0.245, 60, 128, AmortizationType.ANNUITY).toDouble(), 0.0001)

        // 48.63%
        assertEquals(0.4863, effectiveInterestRate(500_000, 0.40, 120, 180, AmortizationType.ANNUITY).toDouble(), 0.0001)

        // 1.01%
        assertEquals(0.0101, effectiveInterestRate(500_000, 0.01, 1, 32, AmortizationType.ANNUITY).toDouble(), 0.0001)
    }

    @Test
    fun testComputeEffectiveInterestRateWithoutTermFees() {
        val noTermFee: Int = 0

        // 1.0%
        assertEquals(0.01, effectiveInterestRate(1_000, 0.01, noTermFee, 5, AmortizationType.ANNUITY).toDouble(), 0.0001)

        // 9.82%
        assertEquals(0.0512, effectiveInterestRate(10_000, 0.05, noTermFee, 24, AmortizationType.ANNUITY).toDouble(), 0.0001)

        // 24.5%
        assertEquals(0.2745, effectiveInterestRate(200_000, 0.245, noTermFee, 128, AmortizationType.ANNUITY).toDouble(), 0.0001)

        // 48.21%
        assertEquals(0.4821, effectiveInterestRate(500_000, 0.40, noTermFee, 180, AmortizationType.ANNUITY).toDouble(), 0.0001)

        // 1.0%
        assertEquals(0.01, effectiveInterestRate(500_000, 0.01, noTermFee, 32, AmortizationType.ANNUITY).toDouble(), 0.0001)
    }
}