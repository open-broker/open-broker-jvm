package io.klira.cloudevents.serialize

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.klira.cloudevents.CloudEvent
import io.klira.cloudevents.parseEventTime
import java.time.Instant
import java.util.*

internal const val CLOUD_EVENTS_VERSION = "0.1"

fun <T, U> cloudEvent(
    event: T,
    eventType: String,
    eventTypeVersion: String,
    source: String,
    contentType: String,
    timestamp: Instant = Instant.now(),
    eventId: String = UUID.randomUUID().toString(),
    serialize: (T) -> U
): CloudEvent<U>
{
    return CloudEvent<U>(
        eventType = eventType,
        eventTypeVersion = eventTypeVersion,
        source = source,
        cloudEventsVersion = CLOUD_EVENTS_VERSION,
        eventID = eventId,
        eventTime = parseEventTime(timestamp),
        contentType = contentType,
        data = serialize(event)
    )
}

fun <T> cloudEventJson(
    event: T,
    eventType: String,
    eventTypeVersion: String,
    source: String,
    timestamp: Instant = Instant.now(),
    eventId: String = UUID.randomUUID().toString()
): CloudEvent<JsonNode> {
    return cloudEvent(
        event = event,
        eventType = eventType,
        eventTypeVersion = eventTypeVersion,
        source = source,
        contentType = "application/json",
        timestamp = timestamp,
        eventId = eventId,
        serialize = ::jsonNode
    )
}

fun <T> jsonNode(e: T): JsonNode {
    val content: String = jsonString(e)
    return jacksonObjectMapper().readTree(content)
}

fun <T> jsonString(e: T): String = jacksonObjectMapper().writeValueAsString(e)

fun cloudEventJson(payload: String): CloudEvent<JsonNode> {
    return jacksonObjectMapper().readValue<CloudEvent<JsonNode>>(payload)
}