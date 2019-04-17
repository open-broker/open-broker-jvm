package org.openbroker.no.mortgage.events

import org.openbroker.common.model.Reference
import org.openbroker.no.privateunsecuredloan.model.LoanInsuranceOffer
import org.openbroker.no.privateunsecuredloan.model.Offer

/**
 * An offer for a loan
 */
data class Offering @JvmOverloads constructor(
    override val brokerReference: Reference,
    val offer: Offer,
    val loanInsuranceOffer: LoanInsuranceOffer? = null
): MortgageEvent