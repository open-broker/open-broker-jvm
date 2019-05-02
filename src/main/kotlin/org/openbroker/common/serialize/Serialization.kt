package org.openbroker.common.serialize

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.openbroker.cloudevents.CloudEvent
import org.openbroker.common.OpenBrokerEvent
import org.openbroker.common.meta.EventType
import org.openbroker.common.meta.eventType

private val mapper: ObjectMapper = jacksonObjectMapper()
    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

private inline fun <reified T: OpenBrokerEvent> parse(json: String): T =
    mapper.readValue(json)

private fun <T: OpenBrokerEvent> parse(json: String, clazz: Class<T>): OpenBrokerEvent =
    mapper.readValue(json, clazz)

fun parseOpenBrokerEvent(payload: String): CloudEvent<OpenBrokerEvent> {
    val cloudEvent: CloudEvent<JsonNode> = mapper.readValue(payload)
    return cloudEvent.toOpenBrokerEvent()
}

fun CloudEvent<JsonNode>.toOpenBrokerEvent(): CloudEvent<OpenBrokerEvent> {
    val data: String = mapper.writeValueAsString(this.data)
    val eventType: EventType<OpenBrokerEvent> = eventType(this.eventType)
    val event: OpenBrokerEvent = parse(data, eventType.clazz)
    return this.withData(event)
}

fun cloudEventType(payload: String): String {
    val node: JsonNode = mapper.readValue(payload)
    return node["eventType"]?.textValue() ?: throw IllegalArgumentException("EventType was null")
}

fun restoreOpenBrokerEvent(event: CloudEvent<*>): CloudEvent<OpenBrokerEvent>? {
    if(event.data == null)
        return null
    val data: String = mapper.writeValueAsString(event.data)
    val type: EventType<OpenBrokerEvent> = eventType(event.eventType)
    val openBrokerEvent: OpenBrokerEvent = parse(data, type.clazz)
    return event.withData(openBrokerEvent)
}

@Suppress("UNCHECKED_CAST")
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