package org.openbroker.se.privateunsecuredloan

import org.openbroker.common.meta.EventType
import org.openbroker.common.meta.EventTypeFactory
import org.openbroker.common.meta.EventTypeQualifier
import org.openbroker.common.meta.QualifiedName
import org.openbroker.se.mortgage.MortgageSweden
import org.openbroker.se.privateunsecuredloan.events.*

enum class PrivateUnsecuredLoanSweden(
    override val clazz: Class<out PrivateUnsecuredLoanEvent>
): EventType<PrivateUnsecuredLoanEvent> {
    APPLICATION_CREATED(ApplicationCreated::class.java),
    DELAYED_PROCESSING(DelayedProcessing::class.java),
    MESSAGE(Message::class.java),
    OFFERING(Offering::class.java),
    REJECTION(Rejection::class.java),
    OFFER_ACCEPTED(OfferAccepted::class.java),
    OFFER_REJECTED(OfferRejected::class.java),
    STATUS_UPDATED(StatusUpdated::class.java),
    DISBURSED(Disbursed::class.java);

    override fun eventName(): QualifiedName = qualifier.withClass(clazz)

    companion object: EventTypeFactory<PrivateUnsecuredLoanEvent> {
        override fun values(): Array<out EventType<PrivateUnsecuredLoanEvent>> =
            PrivateUnsecuredLoanSweden.values()
        override val qualifier: EventTypeQualifier =
            EventTypeQualifier("v0", "se", "PrivateUnsecuredLoan")

        override fun compare(p0: PrivateUnsecuredLoanEvent, p1: PrivateUnsecuredLoanEvent): Int {
            if(p0 is StatusUpdated && p1 is StatusUpdated)
                return p0.status.ordinal - p1.status.ordinal
            return super.compare(p0, p1)
        }
    }
}