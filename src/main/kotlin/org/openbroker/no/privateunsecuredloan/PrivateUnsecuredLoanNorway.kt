package org.openbroker.no.privateunsecuredloan

import org.openbroker.common.meta.EventType
import org.openbroker.common.meta.EventTypeFactory
import org.openbroker.common.meta.EventTypeQualifier
import org.openbroker.common.meta.QualifiedName
import org.openbroker.no.privateunsecuredloan.events.*

enum class PrivateUnsecuredLoanNorway(
    override val clazz: Class<out PrivateUnsecuredLoanEvent>
): EventType<PrivateUnsecuredLoanEvent> {
    APPLICATION_CREATED(ApplicationCreated::class.java),
    MESSAGE(Message::class.java),
    OFFERING(Offering::class.java),
    REJECTION(Rejection::class.java),
    OFFER_ACCEPTED(OfferAccepted::class.java),
    OFFER_REJECTED(OfferRejected::class.java),
    CONTRACT_SIGNED(ContractSigned::class.java),
    DISBURSED(Disbursed::class.java);

    override fun eventName(): QualifiedName = qualifier.withClass(clazz)

    companion object: EventTypeFactory<PrivateUnsecuredLoanEvent> {
        override fun values(): Array<out EventType<PrivateUnsecuredLoanEvent>> =
            PrivateUnsecuredLoanNorway.values()
        override val qualifier: EventTypeQualifier =
            EventTypeQualifier("v0", "no", "PrivateUnsecuredLoan")
    }
}