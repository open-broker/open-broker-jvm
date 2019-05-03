package org.openbroker.no.mortgage

import org.openbroker.common.meta.EventType
import org.openbroker.common.meta.EventTypeFactory
import org.openbroker.common.meta.EventTypeQualifier
import org.openbroker.common.meta.QualifiedName
import org.openbroker.no.mortgage.events.ApplicationCreated
import org.openbroker.no.mortgage.events.MortgageEvent
import org.openbroker.no.mortgage.events.Offering
import org.openbroker.no.mortgage.events.ApplicationRejection
import org.openbroker.no.mortgage.events.Disbursed
import org.openbroker.no.mortgage.events.Message
import org.openbroker.no.mortgage.events.OfferAccepted
import org.openbroker.no.mortgage.events.OfferRejected

enum class MortgageNorway(
    override val clazz: Class<out MortgageEvent>
): EventType<MortgageEvent> {
    APPLICATION_CREATED(ApplicationCreated::class.java),
    OFFERING(Offering::class.java),
    APPLICATION_REJECTION(ApplicationRejection::class.java),
    OFFER_REJECTED(OfferRejected::class.java),
    OFFER_ACCEPTED(OfferAccepted::class.java),
    DISBURSED(Disbursed::class.java),
    MESSAGE(Message::class.java);

    override fun eventName(): QualifiedName = MortgageNorway.qualifier.withClass(clazz)

    companion object: EventTypeFactory<MortgageEvent> {
        override fun values(): Array<out EventType<MortgageEvent>> = MortgageNorway.values()
        override val qualifier: EventTypeQualifier = EventTypeQualifier(
            version = "v0",
            region = "no",
            domain = "Mortgage"
        )
    }
}