package org.openbroker.mortgage

import org.openbroker.common.meta.EventTypeFactory
import org.openbroker.common.meta.EventTypeQualifier
import org.openbroker.mortgage.events.MortgageEvent

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