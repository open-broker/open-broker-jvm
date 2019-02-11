package org.openbroker.privateunsecuredloan.events

import org.openbroker.privateunsecuredloan.model.LoanInsuranceOffer
import org.openbroker.privateunsecuredloan.model.Offer
import org.openbroker.common.model.Reference

/**
 * An offer for a loan
 */
data class Offering @JvmOverloads constructor(
    override val brokerReference: Reference,
    val offer: Offer,
    val loanInsuranceOffer: LoanInsuranceOffer? = null
): PrivateUnsecuredLoanEvent