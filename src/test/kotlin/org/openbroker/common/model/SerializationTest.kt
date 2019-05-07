package org.openbroker.common.model

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.openbroker.cloudevents.CloudEvent
import org.openbroker.common.OpenBrokerEvent
import org.openbroker.common.serialize.parseOpenBrokerEvent
import org.openbroker.se.privateunsecuredloan.TestObjectsJson

class SerializationTest {
    @Test
    fun testOpenBrokerEventWithUnknownProperty() {
        // Verify that this does not throw an exception
        val event: CloudEvent<OpenBrokerEvent> = parseOpenBrokerEvent(TestObjectsJson.disbursedWithAdditionalValue)
        Assertions.assertNotNull(event.data)
    }
}