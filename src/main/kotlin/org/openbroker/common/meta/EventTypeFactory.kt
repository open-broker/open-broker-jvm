package org.openbroker.common.meta

import org.openbroker.common.OpenBrokerEvent

interface EventTypeFactory<T: OpenBrokerEvent>: Comparator<T> {
    val qualifier: EventTypeQualifier
    fun values(): Array<out EventType<T>>

    operator fun invoke(type: String): EventType<out T> {
        return values()
            .firstOrNull { it.eventName().fullName == type }
            ?: throw IllegalArgumentException("Input does not map to a valid type: '$type'")
    }

    operator fun invoke(clazz: Class<*>): EventType<out @JvmWildcard T> {
        return values()
            .firstOrNull { it.clazz == clazz } ?:
        throw IllegalArgumentException("Class does not map to a valid type: '$clazz'")
    }

    operator fun contains(type: String): Boolean = type.startsWith(qualifier.toString())
    operator fun contains(clazz: Class<*>): Boolean = clazz in values().map { it.clazz }

    override fun compare(p0: T, p1: T): Int {
        val i0: Int = values().indexOf(p0::class.java)
        val i1: Int = values().indexOf(p1::class.java)
        return i0 - i1
    }

    fun eventTypeComparator(): Comparator<EventType<T>> {
        return Comparator { p0, p1 ->
            val i0: Int = values().indexOf(p0.clazz)
            val i1: Int = values().indexOf(p1.clazz)
            i0 - i1
        }
    }

    private fun Array<out EventType<T>>.indexOf(clazz: Class<out T>): Int =
        this.map { it.clazz }.indexOf(clazz)
}