package org.openbroker.common.meta

import org.openbroker.common.OpenBrokerEvent

interface EventTypeFactory<T: OpenBrokerEvent>: Comparator<T> {
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

    override fun compare(p0: T, p1: T): Int {
        val i0: Int = classes.indexOf(p0::class.java)
        val i1: Int = classes.indexOf(p1::class.java)
        return i0 - i1
    }
}