package org.openbroker.common.meta

import org.openbroker.common.OpenBrokerEvent
import org.openbroker.mortgage.events.MortgageEvent
import org.openbroker.privateunsecuredloan.events.PrivateUnsecuredLoanEvent

interface EventTypeFactory<T: OpenBrokerEvent> {
    val qualifier: EventTypeQualifier
    val classes: List<Class<out T>>

    fun values(): List<EventType<out T>> = classes.asSequence()
        .map { EventType(it, qualifier) }
        .toList()

    operator fun invoke(type: String): EventType<out T> {
        return values()
            .firstOrNull { it.name == type }
            ?: throw IllegalArgumentException("Input does not map to a valid type: '$type'")
    }

    operator fun invoke(clazz: Class<*>): EventType<out T> {
        return values()
            .firstOrNull { it.clazz == clazz } ?:
            throw IllegalArgumentException("Class does not map to a valid type: '$clazz'")
    }

    operator fun contains(type: String): Boolean = type.startsWith(qualifier.toString())
    operator fun contains(clazz: Class<*>): Boolean = clazz in classes
}

data class EventTypeQualifier(
    val version: String,
    val region: String,
    val domain: String
) {
    companion object {
        const val NAME_SPACE: String = "org.open-broker"
    }

    override fun toString(): String = "$NAME_SPACE.$version.$region.$domain"
}

data class EventType<T: OpenBrokerEvent>(val clazz: Class<out T>, val qualifier: EventTypeQualifier) {
    val name: String = "$qualifier${clazz.simpleName}"
    override fun toString(): String = name
}

fun <T: OpenBrokerEvent> eventType(clazz: Class<out T>): EventType<T> {
    return when(clazz) {
        in EventTypePrivateUnsecuredLoanSweden -> EventTypePrivateUnsecuredLoanSweden(clazz)
        in EventTypeMortgageSweden -> EventTypeMortgageSweden(clazz)
        else -> throw java.lang.IllegalArgumentException("Unsupported class $clazz")
    } as EventType<T>
}

fun <T: OpenBrokerEvent> eventType(eventType: String): EventType<T> {
    return when(eventType) {
        in EventTypePrivateUnsecuredLoanSweden -> EventTypePrivateUnsecuredLoanSweden(eventType)
        in EventTypeMortgageSweden -> EventTypeMortgageSweden(eventType)
        else -> throw java.lang.IllegalArgumentException("Unsupported class $eventType")
    } as EventType<T>
}

fun eventTypeExists(eventType: String): Boolean {
    return when(eventType) {
        in EventTypePrivateUnsecuredLoanSweden -> true
        in EventTypeMortgageSweden -> true
        else -> false
    }
}

object EventTypePrivateUnsecuredLoanSweden: EventTypeFactory<PrivateUnsecuredLoanEvent> {
    override val qualifier: EventTypeQualifier = EventTypeQualifier(
        version = "v0",
        region = "se",
        domain = "PrivateUnsecuredLoan"
    )

    override val classes: List<Class<out PrivateUnsecuredLoanEvent>> = listOf(
        org.openbroker.privateunsecuredloan.events.ApplicationCreated::class.java,
        org.openbroker.privateunsecuredloan.events.DelayedProcessing::class.java,
        org.openbroker.privateunsecuredloan.events.Rejection::class.java,
        org.openbroker.privateunsecuredloan.events.Offering::class.java,
        org.openbroker.privateunsecuredloan.events.OfferAccepted::class.java,
        org.openbroker.privateunsecuredloan.events.OfferRejected::class.java,
        org.openbroker.privateunsecuredloan.events.StatusUpdated::class.java,
        org.openbroker.privateunsecuredloan.events.Disbursed::class.java
    )

}

object EventTypeMortgageSweden: EventTypeFactory<MortgageEvent> {
    override val qualifier: EventTypeQualifier = EventTypeQualifier(
        version = "v0",
        region = "se",
        domain = "Mortgage"
    )

    override val classes: List<Class<out MortgageEvent>> = listOf(
        org.openbroker.mortgage.events.ApplicationCreated::class.java,
        org.openbroker.mortgage.events.Offering::class.java,
        org.openbroker.mortgage.events.ApplicationRejection::class.java,
        org.openbroker.mortgage.events.OfferRejected::class.java,
        org.openbroker.mortgage.events.OfferAccepted::class.java,
        org.openbroker.mortgage.events.ContractSent::class.java,
        org.openbroker.mortgage.events.ContractSigned::class.java,
        org.openbroker.mortgage.events.Disbursed::class.java,
        org.openbroker.mortgage.events.Message::class.java
    )
}