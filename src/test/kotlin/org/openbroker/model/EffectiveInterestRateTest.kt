package org.openbroker.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class EffectiveInterestRateTest {

    @Test
    fun testComputeEffectiveInterestRate() {
        // 9.82%
        assertEquals(0.0982, effectiveInterestRate(10_000, 0.05, 20, 24, AmortizationType.ANNUITY).toDouble(), 0.00001)
    }
}