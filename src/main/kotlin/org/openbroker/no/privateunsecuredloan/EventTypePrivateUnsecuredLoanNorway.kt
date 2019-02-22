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

    )
}