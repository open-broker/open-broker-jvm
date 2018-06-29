package io.klira.cloudevents

import java.time.Instant
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.ZoneOffset

/**
 * Tries to model version 0.1 of Cloud Events
 * https://github.com/cloudevents/spec/blob/v0.1/spec.md#context-attributes
 */
data class CloudEvent<T>(
    /**
     * Type of occurrence which has happened. Often this property
     * is used for routing, observability, policy enforcement, etc.
     *
     * SHOULD be prefixed with a reverse-DNS name. The prefixed domain dictates the organization
     * which defines the semantics of this event type.
     * Examples: `com.github.pull.create`
     */
    val eventType: String,
    /**
     * The version of the eventType. This enables the interpretation of data by
     * eventual consumers, requires the consumer to be knowledgeable about the producer.
     */
    val eventTypeVersion: String? = null,
    /**
     * The version of the CloudEvents specification which the event uses.
     * This enables the interpretation of the context.
     */
    val cloudEventsVersion: String,
    /**
     * This describes the event producer. Often this will include information
     * such as the type of the event source, the organization publishing the event,
     * and some unique idenfitiers. The exact syntax and semantics behind the
     * data encoded in the URI is event producer defined.
     */
    val source: String, // RFC 3986
    /**
     * ID of the event. The semantics of this string are explicitly undefined
     * to ease the implementation of producers. Enables deduplication.
     *
     * MUST be unique within the scope of the producer
     */
    val eventID: String,
    /**
     * Timestamp of when the event happened, formatted as specified in RFC 3986
     */
    val eventTime: String? = null, // RFC 3339
    /**
     * A link to the schema that the data attribute adheres to.
     * If present, MUST adhere to the format specified in RFC 3986
     */
    val schemaURL: String? = null,  // RFC 3986
    /**
     * Describe the data encoding format
     */
    val contentType: String? = null, // RFC 2046
    /**
     * This is for additional metadata and this does not have a mandated structure.
     * This enables a place for custom fields a producer or middleware might
     * want to include and provides a place to test metadata before adding them
     * to the CloudEvents specification.
     *
     * See the [Extensions](https://github.com/cloudevents/spec/blob/v0.1/extensions.md)
     * document for a list of possible properties.
     */
    val extensions: Map<String, Any>? = null,
    /**
     * The event payload. The payload depends on the eventType, schemaURL and
     * eventTypeVersion, the payload is encoded into a media format which is
     * specified by the contentType attribute (e.g. application/json).
     */
    val data: T?
) {
    init {
        require(eventType.isNotEmpty()) { "eventType must be a non-empty string" }

        eventTypeVersion?.let {
            require(it.isNotEmpty()) { "eventTypeVersion if present, must be a non-empty string" }
        }

        require(cloudEventsVersion.isNotEmpty()) { "cloudEventsVersion must be a non-empty string" }
        require(eventID.isNotEmpty()) { "eventID must be a non-empty string" }

        eventTime?.let {
            validateEventTime(eventTime)
        }

        extensions?.let {
            require(it.isNotEmpty()) { "extensions if present, must contain at least one entry"}
        }
    }
}

private val year = Regex("[1-9]\\d{3}")
private val month = Regex("[0-1]\\d")
private val day = Regex("[0-3]\\d")

private val hour = Regex("[0-2]\\d")
private val minutes = Regex("[0-5]\\d")
private val seconds = Regex("[0-6]\\d")
private val milliSeconds = Regex("\\.\\d+")

private val zoneOffset = Regex("(Z|[+\\-][0-1]\\d:[0-5]\\d)")

private val date = Regex("$year-$month-$day")
private val time = Regex("$hour:$minutes:$seconds($milliSeconds)?")

private val rfc3339Regex = Regex("^${date}T$time$zoneOffset\$")

fun validateEventTime(timestamp: String) {
    require(timestamp.matches(rfc3339Regex)) {
        "Invalid RFC3339 timestamp: '$timestamp'"
    }
}

fun parseEventTime(timestamp: String): Instant {
    validateEventTime(timestamp)
    return Instant.parse(timestamp)
}

fun parseEventTime(timestamp: OffsetDateTime): String = timestamp.toString()

fun parseEventTime(timestamp: LocalDateTime, timeZone: ZoneId = ZoneId.systemDefault()): String {
    val dateWithOffset = OffsetDateTime.of(timestamp, timeZone.offset())
    return dateWithOffset.toString()
}

fun parseEventTime(timestamp: Instant = Instant.now(), timeZone: ZoneId = ZoneId.systemDefault()): String {
    val offset: ZoneOffset = timeZone.offset(timestamp)
    return parseEventTime(OffsetDateTime.ofInstant(timestamp, offset))
}

fun ZoneId.offset(time: Instant = Instant.now()): ZoneOffset = rules.getOffset(time)