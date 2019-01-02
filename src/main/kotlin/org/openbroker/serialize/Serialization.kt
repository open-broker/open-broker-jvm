package org.openbroker.serialize

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.openbroker.events.OpenBrokerEvent
import org.openbroker.meta.EventTypePrivateUnsecuredLoan
import org.openbroker.cloudevents.CloudEvent
import org.openbroker.events.ApplicationCreated
import org.openbroker.events.DelayedProcessing
import org.openbroker.events.Disbursed
import org.openbroker.events.OfferAccepted
import org.openbroker.events.OfferRejected
import org.openbroker.events.Offering
import org.openbroker.events.Rejection
import org.openbroker.events.StatusUpdated
import java.time.Instant
import java.util.UUID

@JvmOverloads fun <T: OpenBrokerEvent> openBrokerEvent(
    event: T,
    eventType: EventTypePrivateUnsecuredLoan,
    source: String,
    timestamp: Instant = Instant.now(),
    eventId: String = UUID.randomUUID().toString()
): CloudEvent<T> {
    return CloudEvent(
        data = event,
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
    return CloudEvent(
        data = event,
        eventType = EventTypePrivateUnsecuredLoan(T::class.java).toString(),
        eventTypeVersion = EventTypePrivateUnsecuredLoan.VERSION,
        source = source,
        contentType = "application/json",
        timestamp = timestamp,
        eventId = eventId
    )
}

private typealias EventType = EventTypePrivateUnsecuredLoan
private inline fun <reified T: OpenBrokerEvent> parse(json: String): T
    = jacksonObjectMapper().readValue(json)

fun parseOpenBrokerEvent(payload: String): CloudEvent<OpenBrokerEvent> {
    val cloudEvent: CloudEvent<JsonNode> = jacksonObjectMapper().readValue(payload)
    val data: String = jacksonObjectMapper().writeValueAsString(cloudEvent.data)
    val eventType = EventType(cloudEvent.eventType)
    val eventData: OpenBrokerEvent = when(eventType) {
        EventType.APPLICATION_CREATED -> parse<ApplicationCreated>(data)
        EventType.DELAYED_PROCESSING -> parse<DelayedProcessing>(data)
        EventType.OFFERING -> parse<Offering>(data)
        EventType.REJECTION -> parse<Rejection>(data)
        EventType.OFFER_ACCEPTED -> parse<OfferAccepted>(data)
        EventType.OFFER_REJECTED -> parse<OfferRejected>(data)
        EventType.STATUS_UPDATED -> parse<StatusUpdated>(data)
        EventType.DISBURSED -> parse<Disbursed>(data)
    }
    return CloudEvent(
        eventType = cloudEvent.eventType,
        eventTypeVersion = cloudEvent.eventTypeVersion,
        source = cloudEvent.source,
        cloudEventsVersion = cloudEvent.cloudEventsVersion,
        contentType = cloudEvent.contentType,
        eventID = cloudEvent.eventID,
        eventTime = cloudEvent.eventTime,
        extensions = cloudEvent.extensions,
        schemaURL = cloudEvent.schemaURL,
        data = eventData
    )
}