package io.klira.cloudevents.serialize

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.klira.cloudevents.CloudEvent
import io.klira.cloudevents.parseEventTime
import java.time.Instant
import java.util.*

internal const val CLOUD_EVENTS_VERSION = "0.1"

fun <T> cloudEvent(
    event: T,
    eventType: String,
    eventTypeVersion: String? = null,
    source: String,
    contentType: String? = null,
    timestamp: Instant = Instant.now(),
    eventId: String = UUID.randomUUID().toString()
): CloudEvent<T>
{
    return CloudEvent(
        eventType = eventType,
        eventTypeVersion = eventTypeVersion,
        source = source,
        cloudEventsVersion = CLOUD_EVENTS_VERSION,
        eventID = eventId,
        eventTime = parseEventTime(timestamp),
        contentType = contentType,
        data = event
    )
}

fun <T> jsonString(e: T): String = jacksonObjectMapper().writeValueAsString(e)

inline fun <reified T: Any> cloudEvent(payload: String): CloudEvent<T> {
    return jacksonObjectMapper().readValue(payload)
}