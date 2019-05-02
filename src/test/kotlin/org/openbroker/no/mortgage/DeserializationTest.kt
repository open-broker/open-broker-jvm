package org.openbroker.no.mortgage

import org.junit.jupiter.api.Test
import org.openbroker.no.mortgage.events.ApplicationCreated
import org.openbroker.parseJsonFromFile

class DeserializationTest {

    /**
     * This test will parse a JSON example file for an ApplicationCreated
     * event where only the required values are present. The purpose of this
     * test is to check that this library requires the same value as the
     * specification does. If a required value is missing, an exception is
     * thrown, and this test will fail.
     */
    @Test
    fun parseApplicationCreatedWithOnlyRequiredValues() {
        parseJsonFromFile<ApplicationCreated>("no/mortgage/MortgageApplicationCreated_Required")
    }
}