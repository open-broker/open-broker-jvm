package org.openbroker.se.privateunsecuredloan

import org.openbroker.common.meta.EventTypeFactory
import org.openbroker.common.meta.EventTypeQualifier
import org.openbroker.se.privateunsecuredloan.events.PrivateUnsecuredLoanEvent

object EventTypePrivateUnsecuredLoanSweden: EventTypeFactory<PrivateUnsecuredLoanEvent> {
    override val qualifier: EventTypeQualifier = EventTypeQualifier(
        version = "v0",
        region = "se",
        domain = "PrivateUnsecuredLoan"
    )

    override val classes: List<Class<out PrivateUnsecuredLoanEvent>> = listOf(
        org.openbroker.se.privateunsecuredloan.events.ApplicationCreated::class.java,
        org.openbroker.se.privateunsecuredloan.events.DelayedProcessing::class.java,
        org.openbroker.se.privateunsecuredloan.events.Rejection::class.java,
        org.openbroker.se.privateunsecuredloan.events.Offering::class.java,
        org.openbroker.se.privateunsecuredloan.events.OfferAccepted::class.java,
        org.openbroker.se.privateunsecuredloan.events.OfferRejected::class.java,
        org.openbroker.se.privateunsecuredloan.events.StatusUpdated::class.java,
        org.openbroker.se.privateunsecuredloan.events.Disbursed::class.java
    )
}