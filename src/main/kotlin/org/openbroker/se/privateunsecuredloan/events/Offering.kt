package org.openbroker.se.privateunsecuredloan.events

import org.openbroker.se.privateunsecuredloan.model.LoanInsuranceOffer
import org.openbroker.se.privateunsecuredloan.model.Offer
import org.openbroker.common.model.Reference

/**
 * An offer for a loan
 */
data class Offering @JvmOverloads constructor(
    override val brokerReference: Reference,
    val offerId: Reference? = null,
    val offer: Offer,
    val loanInsuranceOffer: LoanInsuranceOffer? = null
): PrivateUnsecuredLoanEvent