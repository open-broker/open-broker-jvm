package org.openbroker.privateunsecuredloan.serialize

import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.openbroker.privateunsecuredloan.events.PrivateUnsecuredLoanEvent
import org.openbroker.privateunsecuredloan.meta.EventTypePrivateUnsecuredLoan
import org.openbroker.cloudevents.CloudEvent
import org.openbroker.common.OpenBrokerEvent
import org.openbroker.privateunsecuredloan.events.ApplicationCreated
import org.openbroker.privateunsecuredloan.events.DelayedProcessing
import org.openbroker.privateunsecuredloan.events.Disbursed
import org.openbroker.privateunsecuredloan.events.OfferAccepted
import org.openbroker.privateunsecuredloan.events.OfferRejected
import org.openbroker.privateunsecuredloan.events.Offering
import org.openbroker.privateunsecuredloan.events.Rejection
import org.openbroker.privateunsecuredloan.events.StatusUpdated
import java.time.Instant
import java.util.UUID

@JvmOverloads fun <T: OpenBrokerEvent> openBrokerEvent(
    event: T,
    eventType: Class<T>,
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
private inline fun <reified T: PrivateUnsecuredLoanEvent> parse(json: String): T
    = jacksonObjectMapper().readValue(json)

fun parseOpenBrokerEvent(payload: String): CloudEvent<PrivateUnsecuredLoanEvent> {
    val cloudEvent: CloudEvent<JsonNode> = jacksonObjectMapper().readValue(payload)
    val data: String = jacksonObjectMapper().writeValueAsString(cloudEvent.data)
    val eventType = EventType(cloudEvent.eventType)
    val eventData: PrivateUnsecuredLoanEvent = when(eventType) {
        EventType.APPLICATION_CREATED -> parse<ApplicationCreated>(data)
        EventType.DELAYED_PROCESSING -> parse<DelayedProcessing>(data)
        EventType.OFFERING -> parse<Offering>(data)
        EventType.REJECTION -> parse<Rejection>(data)
        EventType.OFFER_ACCEPTED -> parse<OfferAccepted>(data)
        EventType.OFFER_REJECTED -> parse<OfferRejected>(data)
        EventType.STATUS_UPDATED -> parse<StatusUpdated>(data)
        EventType.DISBURSED -> parse<Disbursed>(data)
    }
    return cloudEvent.withData(eventData)
}

fun cloudEventType(payload: String): String {
    val node: JsonNode = jacksonObjectMapper().readValue(payload)
    return node["eventType"]?.textValue() ?: throw IllegalArgumentException("EventType was null")
}

fun restoreOpenBrokerEvent(event: CloudEvent<*>): CloudEvent<PrivateUnsecuredLoanEvent>? {
    if(event.data == null)
        return null
    val data: String = jacksonObjectMapper().writeValueAsString(event.data)
    val type  = EventTypePrivateUnsecuredLoan(event.eventType)
    val privateUnsecuredLoanEvent: PrivateUnsecuredLoanEvent = when(type) {
        EventType.APPLICATION_CREATED -> parse<ApplicationCreated>(data)
        EventType.DELAYED_PROCESSING -> parse<DelayedProcessing>(data)
        EventType.OFFERING -> parse<Offering>(data)
        EventType.REJECTION -> parse<Rejection>(data)
        EventType.OFFER_ACCEPTED -> parse<OfferAccepted>(data)
        EventType.OFFER_REJECTED -> parse<OfferRejected>(data)
        EventType.STATUS_UPDATED -> parse<StatusUpdated>(data)
        EventType.DISBURSED -> parse<Disbursed>(data)
    }

    return event.withData(privateUnsecuredLoanEvent)
}

fun <T: OpenBrokerEvent> CloudEvent<*>.withData(data: T): CloudEvent<OpenBrokerEvent> {
    if(this.data == data)
        return this as CloudEvent<OpenBrokerEvent>
    return CloudEvent(
        eventType = this.eventType,
        eventTypeVersion = this.eventTypeVersion,
        data = data,
        schemaURL = this.schemaURL,
        extensions = this.extensions,
        eventTime = this.eventTime,
        eventID = this.eventID,
        contentType = this.contentType,
        cloudEventsVersion = this.cloudEventsVersion,
        source = this.source
    )
}