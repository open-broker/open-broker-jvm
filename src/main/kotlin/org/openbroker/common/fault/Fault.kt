package org.openbroker.common.fault

import org.openbroker.common.fault.events.DeliveryError
import org.openbroker.common.fault.events.ErrorEvent
import org.openbroker.common.fault.events.ValidationError
import org.openbroker.common.meta.EventType
import org.openbroker.common.meta.EventTypeFactory
import org.openbroker.common.meta.EventTypeQualifier
import org.openbroker.common.meta.QualifiedName

enum class Fault(
    override val clazz: Class<out ErrorEvent>
): EventType<ErrorEvent> {
    DELIVERYERROR(DeliveryError::class.java),
    VALIDATIONERROR(ValidationError::class.java);

    override fun eventName(): QualifiedName = qualifier.withClass(clazz)

    companion object: EventTypeFactory<ErrorEvent> {
        override fun values(): Array<out EventType<ErrorEvent>> =
            Fault.values()
        override val qualifier: EventTypeQualifier =
            EventTypeQualifier("v0", "common", "Fault")
    }
}