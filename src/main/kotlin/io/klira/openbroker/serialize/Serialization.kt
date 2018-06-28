package io.klira.openbroker.serialize

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.klira.cloudevents.CloudEvent
import io.klira.cloudevents.serialize.cloudEvent
import io.klira.cloudevents.serialize.cloudEventJson
import io.klira.openbroker.events.OpenBrokerEvent
import io.klira.openbroker.meta.EventTypePrivateUnsecuredLoan
import java.time.Instant
import java.util.*

inline fun <reified T: OpenBrokerEvent> parseEvent(cloudEvent: CloudEvent<JsonNode>): T? {
    val eventType = EventTypePrivateUnsecuredLoan(cloudEvent.eventType)
    require(T::class.java == eventType.clazz) {
        "Event type mismatch: type is ${T::class.simpleName} but event is $eventType"
    }
    val openBrokerEventNode: JsonNode = cloudEvent.data ?: return null
    return jacksonObjectMapper().readValue(openBrokerEventNode.toString())
}

fun <T: OpenBrokerEvent, U> openBrokerEvent(
    event: T,
    eventType: EventTypePrivateUnsecuredLoan,
    source: String,
    contentType: String,
    timestamp: Instant = Instant.now(),
    eventId: String = UUID.randomUUID().toString(),
    serialize: (T) -> U
): CloudEvent<U> {
    return cloudEvent<T, U>(
        event = event,
        eventType = eventType.toString(),
        eventTypeVersion = EventTypePrivateUnsecuredLoan.VERSION,
        source = source,
        contentType = contentType,
        timestamp = timestamp,
        eventId = eventId,
        serialize = serialize
    )
}

inline fun <reified T: OpenBrokerEvent> openBrokerEventJson(
    event: T,
    source: String,
    timestamp: Instant = Instant.now(),
    eventId: String = UUID.randomUUID().toString()
): CloudEvent<JsonNode> {
    return cloudEventJson(
        event = event,
        eventType = EventTypePrivateUnsecuredLoan(T::class.java).toString(),
        eventTypeVersion = EventTypePrivateUnsecuredLoan.VERSION,
        source = source,
        timestamp = timestamp,
        eventId = eventId
    )
}