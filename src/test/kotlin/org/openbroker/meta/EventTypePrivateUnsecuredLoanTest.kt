package org.openbroker.meta

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class EventTypePrivateUnsecuredLoanTest {

    @Test
    fun testCorrectPascalCasingOnEventType() {
        val expected = "org.open-broker.v0.se.PrivateUnsecuredLoanApplicationCreated"
        val actual = EventTypePrivateUnsecuredLoan.APPLICATION_CREATED.toString()
        assertEquals(expected, actual)
    }

    @Test
    fun testInvokeFunction() {
        val expected = EventTypePrivateUnsecuredLoan.APPLICATION_CREATED
        val actual = EventTypePrivateUnsecuredLoan("org.open-broker.v0.se.PrivateUnsecuredLoanApplicationCreated")
        assertEquals(expected, actual)
    }
}