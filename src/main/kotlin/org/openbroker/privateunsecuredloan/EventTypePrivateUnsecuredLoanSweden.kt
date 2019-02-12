package org.openbroker.privateunsecuredloan

import org.openbroker.common.meta.EventTypeFactory
import org.openbroker.common.meta.EventTypeQualifier
import org.openbroker.privateunsecuredloan.events.PrivateUnsecuredLoanEvent

object EventTypePrivateUnsecuredLoanSweden: EventTypeFactory<PrivateUnsecuredLoanEvent> {
    override val qualifier: EventTypeQualifier = EventTypeQualifier(
        version = "v0",
        region = "se",
        domain = "PrivateUnsecuredLoan"
    )

    override val classes: List<Class<out PrivateUnsecuredLoanEvent>> = listOf(
        org.openbroker.privateunsecuredloan.events.ApplicationCreated::class.java,
        org.openbroker.privateunsecuredloan.events.DelayedProcessing::class.java,
        org.openbroker.privateunsecuredloan.events.Rejection::class.java,
        org.openbroker.privateunsecuredloan.events.Offering::class.java,
        org.openbroker.privateunsecuredloan.events.OfferAccepted::class.java,
        org.openbroker.privateunsecuredloan.events.OfferRejected::class.java,
        org.openbroker.privateunsecuredloan.events.StatusUpdated::class.java,
        org.openbroker.privateunsecuredloan.events.Disbursed::class.java
    )
}