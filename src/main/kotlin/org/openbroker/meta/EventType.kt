package org.openbroker.meta

import io.klira.openbroker.events.ApplicationCreated
import io.klira.openbroker.events.DelayedProcessing
import io.klira.openbroker.events.Disbursed
import io.klira.openbroker.events.OfferAccepted
import io.klira.openbroker.events.OfferRejected
import io.klira.openbroker.events.Offering
import io.klira.openbroker.events.Rejection
import io.klira.openbroker.events.StatusUpdated

//org.open-broker.v0.se.PrivateUnsecuredLoanApplication

enum class EventTypePrivateUnsecuredLoan(val clazz: Class<*> = Nothing::class.java) {
    APPLICATION_CREATED(ApplicationCreated::class.java),
    DELAYED_PROCESSING(DelayedProcessing::class.java),
    DISBURSED(Disbursed::class.java),
    OFFER_ACCEPTED(OfferAccepted::class.java),
    OFFER_REJECTED(OfferRejected::class.java),
    OFFERING(Offering::class.java),
    REJECTION(Rejection::class.java),
    STATUS_UPDATED(StatusUpdated::class.java);

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