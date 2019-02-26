package org.openbroker.se.mortgage

import org.openbroker.common.meta.EventType
import org.openbroker.common.meta.EventTypeFactory
import org.openbroker.common.meta.EventTypeQualifier
import org.openbroker.common.meta.QualifiedName
import org.openbroker.se.mortgage.events.MortgageEvent
import org.openbroker.se.mortgage.events.ApplicationCreated
import org.openbroker.se.mortgage.events.ApplicationRejection
import org.openbroker.se.mortgage.events.ContractSent
import org.openbroker.se.mortgage.events.ContractSigned
import org.openbroker.se.mortgage.events.Disbursed
import org.openbroker.se.mortgage.events.Message
import org.openbroker.se.mortgage.events.OfferAccepted
import org.openbroker.se.mortgage.events.OfferRejected
import org.openbroker.se.mortgage.events.Offering

enum class MortgageSweden(
    override val clazz: Class<out MortgageEvent>
): EventType<MortgageEvent> {
    APPLICATION_CREATED(ApplicationCreated::class.java),
    OFFERING(Offering::class.java),
    APPLICATION_REJECTION(ApplicationRejection::class.java),
    OFFER_REJECTED(OfferRejected::class.java),
    OFFER_ACCEPTED(OfferAccepted::class.java),
    CONTRACT_SENT(ContractSent::class.java),
    CONTRACT_SIGNED(ContractSigned::class.java),
    DISBURSED(Disbursed::class.java),
    MESSAGE(Message::class.java);

    override fun eventName(): QualifiedName = qualifier.withClass(clazz)

    companion object: EventTypeFactory<MortgageEvent> {
        override fun values(): Array<out EventType<MortgageEvent>> = MortgageSweden.values()
        override val qualifier: EventTypeQualifier = EventTypeQualifier(
            version = "v0",
            region = "se",
            domain = "Mortgage"
        )
    }
}