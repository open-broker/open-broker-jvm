package org.openbroker.se.mortgage

import org.openbroker.common.meta.EventTypeFactory
import org.openbroker.common.meta.EventTypeQualifier
import org.openbroker.se.mortgage.events.ApplicationCreated
import org.openbroker.se.mortgage.events.ApplicationRejection
import org.openbroker.se.mortgage.events.ContractSent
import org.openbroker.se.mortgage.events.ContractSigned
import org.openbroker.se.mortgage.events.Disbursed
import org.openbroker.se.mortgage.events.Message
import org.openbroker.se.mortgage.events.MortgageEvent
import org.openbroker.se.mortgage.events.OfferAccepted
import org.openbroker.se.mortgage.events.OfferRejected
import org.openbroker.se.mortgage.events.Offering

object EventTypeMortgageSweden: EventTypeFactory<MortgageEvent> {
    override val qualifier: EventTypeQualifier = EventTypeQualifier(
        version = "v0",
        region = "se",
        domain = "Mortgage"
    )

    override val classes: List<Class<out MortgageEvent>> = listOf(
        ApplicationCreated::class.java,
        Offering::class.java,
        ApplicationRejection::class.java,
        OfferRejected::class.java,
        OfferAccepted::class.java,
        ContractSent::class.java,
        ContractSigned::class.java,
        Disbursed::class.java,
        Message::class.java
    )
}