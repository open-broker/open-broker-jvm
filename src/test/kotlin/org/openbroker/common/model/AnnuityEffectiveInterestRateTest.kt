package org.openbroker.common.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.openbroker.common.loancosts.Annuity

class AnnuityEffectiveInterestRateTest {

    @Test
    fun testComputeEffectiveInterestRate() {
        Annuity.apply {
            // 5.1%
            assertEquals(0.051, effectiveInterestRate(1_000, 0.01, 2, 5).toDouble(), 0.0001)

            // 9.82%
            assertEquals(0.0982, effectiveInterestRate(10_000, 0.05, 20, 24).toDouble(), 0.0001)

            // 27.97%
            assertEquals(0.2797, effectiveInterestRate(200_000, 0.245, 60, 128).toDouble(), 0.0001)

            // 48.63%
            assertEquals(0.4863, effectiveInterestRate(500_000, 0.40, 120, 180).toDouble(), 0.0001)

            // 1.01%
            assertEquals(0.0101, effectiveInterestRate(500_000, 0.01, 1, 32).toDouble(), 0.0001)
        }
    }

    @Test
    fun testComputeEffectiveInterestRateWithoutTermFees() {
        val noTermFee: Int = 0

        Annuity.apply {
            // 1.0%
            assertEquals(0.01, effectiveInterestRate(1_000, 0.01, noTermFee, 5).toDouble(), 0.0001)

            // 9.82%
            assertEquals(0.0512, effectiveInterestRate(10_000, 0.05, noTermFee, 24).toDouble(), 0.0001)

            // 24.5%
            assertEquals(0.2745, effectiveInterestRate(200_000, 0.245, noTermFee, 128).toDouble(), 0.0001)

            // 48.21%
            assertEquals(0.4821, effectiveInterestRate(500_000, 0.40, noTermFee, 180).toDouble(), 0.0001)

            // 1.0%
            assertEquals(0.01, effectiveInterestRate(500_000, 0.01, noTermFee, 32).toDouble(), 0.0001)
        }
    }
}