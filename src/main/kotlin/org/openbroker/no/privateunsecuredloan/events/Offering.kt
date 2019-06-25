package org.openbroker.no.privateunsecuredloan.events

import org.openbroker.common.model.Reference
import org.openbroker.no.privateunsecuredloan.model.LoanInsuranceOffer
import org.openbroker.no.privateunsecuredloan.model.Offer

/**
 * An offer for a loan
 */
data class Offering @JvmOverloads constructor(
    override val brokerReference: Reference,
    val offerId: Reference? = null,
    val offer: Offer,
    val loanInsuranceOffer: LoanInsuranceOffer? = null
): PrivateUnsecuredLoanEvent