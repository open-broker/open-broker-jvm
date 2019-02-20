package org.openbroker.common

import org.openbroker.cloudevents.CloudEvent
import org.openbroker.common.meta.eventType
import org.openbroker.common.model.Reference
import java.time.Instant
import java.util.*

/**
 * An interface for all Open Broker events
 */
interface OpenBrokerEvent {
    /**
     * A reference-id used by the broker to identify the application
     */
    val brokerReference: Reference
}

@JvmName("create")
@JvmOverloads
fun <T: OpenBrokerEvent> openBrokerEvent(
    event: T,
    eventType: Class<T>,
    source: String,
    timestamp: Instant = Instant.now(),
    eventId: String = UUID.randomUUID().toString()
): CloudEvent<T> {
    val type = org.openbroker.common.meta.eventType(eventType)
    return CloudEvent(
        data = event,
        eventType = type.name,
        eventTypeVersion = type.qualifier.version,
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
    val type = eventType(event::class.java)
    return CloudEvent(
        data = event,
        eventType = type.name,
        eventTypeVersion = type.qualifier.version,
        source = source,
        contentType = "application/json",
        timestamp = timestamp,
        eventId = eventId
    )
}