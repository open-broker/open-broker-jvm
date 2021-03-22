package org.openbroker.common.meta

enum class Domain {
    PrivateUnsecuredLoan,
    Mortgage,
    Fault;

    companion object {
        fun fromEventType(eventType: String): Domain {
            return values().firstOrNull { eventType.contains(it.name) }
                ?: throw IllegalArgumentException("Could not match event type to domain: '$eventType'")
        }
    }
}