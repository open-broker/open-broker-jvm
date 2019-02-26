package org.openbroker.common.meta

import org.openbroker.common.OpenBrokerEvent

/**
 * Qualifier for an event. Properties used are version, region, domain
 * and namespace.
 */
data class EventTypeQualifier(
    val version: String,
    val region: String,
    val domain: String
) {
    companion object {
        const val NAME_SPACE: String = "org.open-broker"
    }

    override fun toString(): String = "$NAME_SPACE.$version.$region.$domain"

    fun <T: OpenBrokerEvent> withClass(clazz: Class<T>): QualifiedName =
        QualifiedName.fromClass(this, clazz)
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
    }
}