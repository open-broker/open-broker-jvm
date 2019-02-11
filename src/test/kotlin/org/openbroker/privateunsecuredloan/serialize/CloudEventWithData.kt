package org.openbroker.privateunsecuredloan.serialize

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.openbroker.TestObjects
import org.openbroker.cloudevents.CloudEvent
import org.openbroker.cloudevents.cloudEvent
import org.openbroker.privateunsecuredloan.events.ApplicationCreated
import org.openbroker.privateunsecuredloan.events.PrivateUnsecuredLoanEvent

class CloudEventWithData {
    @Test
    fun testCloudEventWithSameData() {
        val event: CloudEvent<ApplicationCreated> = cloudEvent(TestObjects.fullApplicationCreatedJson)
        val eventErasedType: CloudEvent<*> = event.copy()
        val eventRestoredType: CloudEvent<PrivateUnsecuredLoanEvent> = eventErasedType.withData(event.data!!)
        assertEquals(eventRestoredType.data, event.data)
    }

    @Test
    fun testCloudEventWithErasedTypeViaJacksonData() {
        val event: CloudEvent<ApplicationCreated> = cloudEvent(TestObjects.fullApplicationCreatedJson)
        val serialized: String = jacksonObjectMapper().writeValueAsString(event)
        val deserialized: CloudEvent<*> = jacksonObjectMapper().readValue(serialized)
        val eventRestoredType: CloudEvent<PrivateUnsecuredLoanEvent> = deserialized.withData(event.data!!)
        assertEquals(eventRestoredType.data, event.data)
    }
}