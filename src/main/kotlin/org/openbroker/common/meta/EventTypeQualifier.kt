package org.openbroker.common.meta

import org.openbroker.common.OpenBrokerEvent
import org.openbroker.common.requireMatchRegex

const val NAME_SPACE: String = "org.open-broker"

/**
 * Qualifier for an event. Properties used are version, region, domain
 * and namespace.
 */
data class EventTypeQualifier(
    val version: String,
    val region: String,
    val domain: String
) {
    init {
        version.requireMatchRegex(Regex("^v\\d+$"), "version")
    }

    override fun toString(): String = "$NAME_SPACE.$version.$region.$domain"

    fun <T: OpenBrokerEvent> withClass(clazz: Class<T>): QualifiedName =
        QualifiedName.fromClass(this, clazz)

    companion object {
        fun fromEvent(eventType: String): EventTypeQualifier {
            require(eventType.startsWith(NAME_SPACE)) { "Illegal event type: $eventType" }
            val parts: List<String> = eventType.removePrefix("$NAME_SPACE.").split(".")
            require(parts.size >= 3) { "Invalid eventType format: $eventType" }
            val domain = Domain.fromEventType(eventType)
            return EventTypeQualifier(
                version = parts[0],
                region = parts[1],
                domain = domain.name
            )
        }
    }
}

data class QualifiedName internal constructor(
    val qualifier: EventTypeQualifier,
    val name: String
) {

    val fullName: String = "$qualifier$name"

    override fun toString(): String = fullName

    companion object {
        fun <T: OpenBrokerEvent> fromEvent(
            qualifier: EventTypeQualifier,
            event: T
        ): QualifiedName {
            return fromClass(qualifier, event.javaClass)
        }

        fun <T: OpenBrokerEvent> fromClass(
            qualifier: EventTypeQualifier,
            eventClass: Class<T>
        ): QualifiedName {
            return QualifiedName(qualifier, eventClass.simpleName)
        }

        fun fromString(
            string: String
        ): QualifiedName {
            require(string.startsWith(NAME_SPACE)) { "Name should start with: $NAME_SPACE" }
            val parts: List<String> = string.split(Regex("\\."))
            require(parts.size == 5){ "Invalid format: $string" }
            val event: String = parts[4]
            val domain: String = Domain.fromEventType(event).name
            val qualifier = EventTypeQualifier(version = parts[2], region = parts[3], domain = domain)
            val eventType: String = event.removePrefix(domain)
            return QualifiedName(qualifier, eventType)
        }
    }
}