package org.openbroker.se.privateunsecuredloan.meta

import org.openbroker.se.privateunsecuredloan.events.DelayedProcessing
import org.openbroker.se.privateunsecuredloan.events.Disbursed
import org.openbroker.se.privateunsecuredloan.events.OfferAccepted
import org.openbroker.se.privateunsecuredloan.events.OfferRejected
import org.openbroker.se.privateunsecuredloan.events.Offering
import org.openbroker.se.privateunsecuredloan.events.PrivateUnsecuredLoanEvent
import org.openbroker.se.privateunsecuredloan.events.Rejection
import org.openbroker.se.privateunsecuredloan.events.StatusUpdated

@Deprecated("Use generic interface EventTypeFactory for PrivateUnsecuredLoan SE")
enum class EventTypePrivateUnsecuredLoan(val clazz: Class<out PrivateUnsecuredLoanEvent>) {
    APPLICATION_CREATED(org.openbroker.se.privateunsecuredloan.events.ApplicationCreated::class.java),
    DELAYED_PROCESSING(DelayedProcessing::class.java),
    OFFERING(Offering::class.java),
    REJECTION(Rejection::class.java),
    OFFER_ACCEPTED(OfferAccepted::class.java),
    OFFER_REJECTED(OfferRejected::class.java),
    STATUS_UPDATED(StatusUpdated::class.java),
    DISBURSED(Disbursed::class.java);

    override fun toString(): String {
        return "$NAME_SPACE.$VERSION.$REGION.PrivateUnsecuredLoan${name.pascalCase()}"
    }

    companion object {
        @JvmStatic
        operator fun invoke(input: String): EventTypePrivateUnsecuredLoan {
            return values()
                .firstOrNull { it.toString() == input }
                ?: throw IllegalArgumentException("Input does not map to a valid type: '$input'")
        }
        @JvmStatic
        operator fun invoke(clazz: Class<*>): EventTypePrivateUnsecuredLoan {
            return values()
                .filter { it.clazz != Nothing::class.java }
                .firstOrNull { it.clazz == clazz }
                ?: throw IllegalArgumentException("Class does not map to a valid type: '$clazz'")
        }
        const val NAME_SPACE = "org.open-broker"
        const val VERSION = "v0"
        const val REGION = "se"
    }
}

private fun String.pascalCase(): String = this.toLowerCase()
    .capitalize()
    .replace(Regex("_[a-z]")) { result: MatchResult ->
        result.value[1].toString().toUpperCase()
    }