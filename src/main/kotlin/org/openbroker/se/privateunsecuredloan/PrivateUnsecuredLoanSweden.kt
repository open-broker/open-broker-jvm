package org.openbroker.se.privateunsecuredloan

import org.openbroker.common.meta.EventType
import org.openbroker.common.meta.EventTypeFactory
import org.openbroker.common.meta.EventTypeQualifier
import org.openbroker.se.privateunsecuredloan.events.ApplicationCreated
import org.openbroker.se.privateunsecuredloan.events.DelayedProcessing
import org.openbroker.se.privateunsecuredloan.events.Disbursed
import org.openbroker.se.privateunsecuredloan.events.OfferAccepted
import org.openbroker.se.privateunsecuredloan.events.OfferRejected
import org.openbroker.se.privateunsecuredloan.events.Offering
import org.openbroker.se.privateunsecuredloan.events.PrivateUnsecuredLoanEvent
import org.openbroker.se.privateunsecuredloan.events.Rejection
import org.openbroker.se.privateunsecuredloan.events.StatusUpdated

enum class PrivateUnsecuredLoanSweden(
    override val clazz: Class<out PrivateUnsecuredLoanEvent>
): EventType<PrivateUnsecuredLoanEvent> {
    APPLICATION_CREATED(ApplicationCreated::class.java),
    DELAYED_PROCESSING(DelayedProcessing::class.java),
    OFFERING(Offering::class.java),
    REJECTION(Rejection::class.java),
    OFFER_ACCEPTED(OfferAccepted::class.java),
    OFFER_REJECTED(OfferRejected::class.java),
    STATUS_UPDATED(StatusUpdated::class.java),
    DISBURSED(Disbursed::class.java);

//    override fun compare(p0: PrivateUnsecuredLoanEvent, p1: PrivateUnsecuredLoanEvent): Int {
//        if(p0 is StatusUpdated && p1 is StatusUpdated)
//            return p0.status.ordinal - p1.status.ordinal
//        return super.compare(p0, p1)
//    }

    companion object: EventTypeFactory<PrivateUnsecuredLoanEvent> {
        override fun values(): Array<out EventType<PrivateUnsecuredLoanEvent>> =
            PrivateUnsecuredLoanSweden.values()
        override val qualifier: EventTypeQualifier =
            EventTypeQualifier("v0", "se", "PrivateUnsecuredLoan")
    }
}