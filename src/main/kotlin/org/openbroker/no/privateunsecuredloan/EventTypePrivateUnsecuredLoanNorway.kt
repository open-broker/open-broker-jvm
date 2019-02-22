package org.openbroker.no.privateunsecuredloan

import org.openbroker.common.meta.EventTypeFactory
import org.openbroker.common.meta.EventTypeQualifier
import org.openbroker.no.privateunsecuredloan.events.PrivateUnsecuredLoanEvent

object EventTypePrivateUnsecuredLoanNorway: EventTypeFactory<PrivateUnsecuredLoanEvent> {
    override val qualifier: EventTypeQualifier = EventTypeQualifier(
        version = "v0",
        region = "no",
        domain = "PrivateUnsecuredLoan"
    )

    override val classes: List<Class<out PrivateUnsecuredLoanEvent>> = listOf(
        org.openbroker.no.privateunsecuredloan.events.ApplicationCreated::class.java,
        org.openbroker.no.privateunsecuredloan.events.Message::class.java,
        org.openbroker.no.privateunsecuredloan.events.Rejection::class.java,
        org.openbroker.no.privateunsecuredloan.events.Offering::class.java,
        org.openbroker.no.privateunsecuredloan.events.OfferAccepted::class.java,
        org.openbroker.no.privateunsecuredloan.events.OfferRejected::class.java,
        org.openbroker.no.privateunsecuredloan.events.Disbursed::class.java
    )
}