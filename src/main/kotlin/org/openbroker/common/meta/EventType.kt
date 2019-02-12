package org.openbroker.common.meta

import org.openbroker.common.OpenBrokerEvent
import org.openbroker.se.mortgage.EventTypeMortgageSweden
import org.openbroker.se.privateunsecuredloan.EventTypePrivateUnsecuredLoanSweden

/**
 * An EventType acts as bridge between the formal type
 * name of a event such as it is named in the `eventType`
 * property in a CloudEvent, and the corresponding class
 * that this event type represents.
 */
data class EventType<T: OpenBrokerEvent>(
    val clazz: Class<out T>,
    val qualifier: EventTypeQualifier
): Comparable<EventType<T>> {
    val name: String = "$qualifier${clazz.simpleName}"
    override fun toString(): String = name
    override fun compareTo(other: EventType<T>): Int =
        this.name.compareTo(other.name)
}

private val knownOpenBrokerDomains: List<EventTypeFactory<*>> = listOf(
    org.openbroker.se.privateunsecuredloan.EventTypePrivateUnsecuredLoanSweden,
    EventTypeMortgageSweden
)

fun <T: OpenBrokerEvent> eventType(clazz: Class<out T>): EventType<T> {
    val type: EventTypeFactory<*> = knownOpenBrokerDomains.firstOrNull { clazz in it }
        ?: throw java.lang.IllegalArgumentException("Unsupported class $clazz")
    return type(clazz) as EventType<T>
}

fun <T: OpenBrokerEvent> eventType(eventType: String): EventType<T> {
    val type: EventTypeFactory<*> = knownOpenBrokerDomains.firstOrNull { eventType in it }
        ?: throw java.lang.IllegalArgumentException("Unsupported type $eventType")
    return type(eventType) as EventType<T>
}

fun eventTypeIsKnown(eventType: String): Boolean =
    knownOpenBrokerDomains.any { eventType in it }