package org.openbroker.events

import org.openbroker.model.LoanInsuranceOffer
import org.openbroker.model.Offer
import org.openbroker.model.Reference

/**
 * An offer for a loan
 */
data class Offering(
    override val brokerReference: Reference,
    val offer: Offer,
    val loanInsurance: LoanInsuranceOffer? = null
): OpenBrokerEvent