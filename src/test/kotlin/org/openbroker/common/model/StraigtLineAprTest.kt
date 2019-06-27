package org.openbroker.common.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.openbroker.common.loancosts.StraightLine

class StraightLineAprTest {
    @Disabled
    @Test
    fun testComputeEffectiveInterestRate() {
        StraightLine.apply {
            // 5.1%
            assertEquals(0.051, effectiveInterestRate(1_000, 0.01, 0, 2, 5).toDouble(), 0.0001)

            // 9.9%
            assertEquals(0.099, effectiveInterestRate(10_000, 0.05, 0, 20, 24).toDouble(), 0.0001)

            // 28.09%
            assertEquals(0.2809, effectiveInterestRate(200_000, 0.245, 0, 60, 128).toDouble(), 0.0001)

            // 48.71%
            assertEquals(0.4871, effectiveInterestRate(500_000, 0.40, 0, 120, 180).toDouble(), 0.0001)

            // 1.01%
            assertEquals(0.0101, effectiveInterestRate(500_000, 0.01, 0, 1, 32).toDouble(), 0.0001)
        }
    }

    @Disabled
    @Test
    fun testComputeEffectiveInterestRateWithoutTermFees() {
        val noTermFee: Int = 0

        StraightLine.apply {
            // 1.0%
            assertEquals(0.01, effectiveInterestRate(1_000, 0.01, 0, noTermFee, 5).toDouble(), 0.0001)

            // 5.12%
            assertEquals(0.0512, effectiveInterestRate(10_000, 0.05, 0, noTermFee, 24).toDouble(), 0.0001)

            // 27.45%
            assertEquals(0.2745, effectiveInterestRate(200_000, 0.245, 0, noTermFee, 128).toDouble(), 0.0001)

            // 48.21%
            assertEquals(0.4821, effectiveInterestRate(500_000, 0.40, 0, noTermFee, 180).toDouble(), 0.0001)

            // 1.0%
            assertEquals(0.01, effectiveInterestRate(500_000, 0.01, 0, noTermFee, 32).toDouble(), 0.0001)
        }
    }
}