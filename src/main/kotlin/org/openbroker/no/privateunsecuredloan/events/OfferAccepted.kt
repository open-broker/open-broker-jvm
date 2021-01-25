package org.openbroker.no.privateunsecuredloan.events

import org.openbroker.common.model.Reference
import org.openbroker.common.obfuscateDigits
import org.openbroker.common.requireMin

data class OfferAccepted @JvmOverloads constructor(
    override val brokerReference: Reference,
    val offerId: Reference? = null,
    val bankAccount: String? = null,
    val requestedCredit: Int? = null,
    val ssn: String? = null,
    val ssnCoapplicant: String? = null,
    val emailAddress: String? = null,
    val emailAddressCoapplicant: String? = null
) : PrivateUnsecuredLoanEvent {
    init {
        requestedCredit.requireMin(1, "requestedCredit")

        val ssnRegex = Regex("^[0-9]{11}$")
        ssn?.let {
            require(it.matches(ssnRegex)) {
                "Invalid applicant's SSN: '${obfuscateDigits(it)}'"
            }
        }
        ssnCoapplicant?.let {
            require(it.matches(ssnRegex)) {
                "Invalid co-applicant's SSN: '${obfuscateDigits(it)}'"
            }
        }

    }
}