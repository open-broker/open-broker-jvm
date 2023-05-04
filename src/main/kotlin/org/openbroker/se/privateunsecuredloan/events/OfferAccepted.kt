package org.openbroker.se.privateunsecuredloan.events

import org.openbroker.se.model.BankAccount
import org.openbroker.common.model.Reference
import org.openbroker.common.obfuscateDigits
import org.openbroker.common.requireMatchRegex
import org.openbroker.common.requireMin

data class OfferAccepted @JvmOverloads constructor(
    override val brokerReference: Reference,
    val offerId: Reference? = null,
    val bankAccount: BankAccount? = null,
    val requestedCredit: Int? = null,
    val termMonths: Int? = null,
    val ssn: String? = null,
    val ssnCoapplicant: String? = null,
    val emailAddress: String? = null,
    val emailAddressCoapplicant: String? = null,
    val extensions: Map<String, Any> = emptyMap()
) : PrivateUnsecuredLoanEvent {
    init {
        requestedCredit.requireMin(1, "requestedCredit")
        termMonths.requireMin(1, "termMonths")

        val ssnRegex = Regex("^[0-9]{12}$")
        ssn?.requireMatchRegex(ssnRegex, "ssn")
        ssnCoapplicant?.requireMatchRegex(ssnRegex, "ssnCoapplicant")
    }
}