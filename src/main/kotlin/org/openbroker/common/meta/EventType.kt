package org.openbroker.common.meta

import org.openbroker.common.OpenBrokerEvent
import org.openbroker.common.fault.Fault
import org.openbroker.no.mortgage.MortgageNorway
import org.openbroker.no.privateunsecuredloan.PrivateUnsecuredLoanNorway
import org.openbroker.se.mortgage.MortgageSweden
import org.openbroker.se.privateunsecuredloan.PrivateUnsecuredLoanSweden

/**
 * An EventType acts as bridge between the formal type
 * name of a event such as it is named in the `eventType`
 * property in a CloudEvent, and the corresponding class
 * that this event type represents.
 */

interface EventType<T: OpenBrokerEvent>{
    val clazz: Class<out T>
    fun eventName(): QualifiedName
}

private val knownOpenBrokerDomains: List<EventTypeFactory<*>> = listOf(
    PrivateUnsecuredLoanNorway,
    PrivateUnsecuredLoanSweden,
    MortgageSweden,
    MortgageNorway,
    Fault
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