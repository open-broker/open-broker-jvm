package org.openbroker.privateunsecuredloan.serialize

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.openbroker.TestObjects
import org.openbroker.cloudevents.CloudEvent
import org.openbroker.cloudevents.cloudEvent
import org.openbroker.privateunsecuredloan.events.ApplicationCreated
import org.openbroker.privateunsecuredloan.events.PrivateUnsecuredLoanEvent

class RestorePrivateUnsecuredLoanEvent {

    @Test
    fun testCloudEventWithSameData() {
        val event: CloudEvent<ApplicationCreated> = cloudEvent(TestObjects.fullApplicationCreatedJson)
        val eventErasedType: CloudEvent<*> = event.copy()
        val eventRestoredType: CloudEvent<PrivateUnsecuredLoanEvent>? = restoreOpenBrokerEvent(eventErasedType)
        assertNotNull(eventRestoredType)
        assertEquals(eventRestoredType, event)
    }

    @Test
    fun testCloudEventWithErasedTypeViaJacksonData() {
        val event: CloudEvent<ApplicationCreated> = cloudEvent(TestObjects.fullApplicationCreatedJson)
        val serialized: String = jacksonObjectMapper().writeValueAsString(event)
        val deserialized: CloudEvent<*> = jacksonObjectMapper().readValue(serialized)
        val eventRestoredType: CloudEvent<PrivateUnsecuredLoanEvent>? = restoreOpenBrokerEvent(deserialized)
        assertNotNull(eventRestoredType)
        assertEquals(eventRestoredType, event)
    }

    @Test
    fun testCloudEventWithWrongType() {
        val event: CloudEvent<String> = CloudEvent(eventType = "SomeType", data = "Hello", source = "test")
        assertThrows<IllegalArgumentException> { restoreOpenBrokerEvent(event) }
    }

    @Test
    fun testCloudEventWithNullData() {
        val event: CloudEvent<String> = CloudEvent(eventType = "SomeType", data = null, source = "test")
        val eventRestoredType: CloudEvent<PrivateUnsecuredLoanEvent>? = restoreOpenBrokerEvent(event)
        assertNull(eventRestoredType)
    }
}