package io.klira.openbroker.serialize

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.klira.cloudevents.CloudEvent
import io.klira.cloudevents.serialize.cloudEvent
import io.klira.openbroker.events.OpenBrokerEvent
import io.klira.openbroker.meta.EventTypePrivateUnsecuredLoan
import java.time.Instant
import java.util.*

fun <T: OpenBrokerEvent> openBrokerEvent(
    event: T,
    eventType: EventTypePrivateUnsecuredLoan,
    source: String,
    timestamp: Instant = Instant.now(),
    eventId: String = UUID.randomUUID().toString()
): CloudEvent<T> {
    return cloudEvent(
        event = event,
        eventType = eventType.toString(),
        eventTypeVersion = EventTypePrivateUnsecuredLoan.VERSION,
        source = source,
        contentType = "application/json",
        timestamp = timestamp,
        eventId = eventId
    )
}

inline fun <reified T: OpenBrokerEvent> openBrokerEvent(
    event: T,
    source: String,
    timestamp: Instant = Instant.now(),
    eventId: String = UUID.randomUUID().toString()
): CloudEvent<T> {
    return cloudEvent(
        event = event,
        eventType = EventTypePrivateUnsecuredLoan(T::class.java).toString(),
        eventTypeVersion = EventTypePrivateUnsecuredLoan.VERSION,
        source = source,
        timestamp = timestamp,
        eventId = eventId
    )
}