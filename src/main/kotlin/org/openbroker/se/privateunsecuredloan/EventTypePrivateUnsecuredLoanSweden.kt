package org.openbroker.se.privateunsecuredloan

import org.openbroker.common.meta.EventTypeFactory
import org.openbroker.common.meta.EventTypeQualifier
import org.openbroker.se.privateunsecuredloan.events.PrivateUnsecuredLoanEvent
import org.openbroker.se.privateunsecuredloan.events.StatusUpdated

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

    override fun compare(p0: PrivateUnsecuredLoanEvent, p1: PrivateUnsecuredLoanEvent): Int {
        if(p0 is StatusUpdated && p1 is StatusUpdated)
            return p0.status.ordinal - p1.status.ordinal
        return super.compare(p0, p1)
    }
}