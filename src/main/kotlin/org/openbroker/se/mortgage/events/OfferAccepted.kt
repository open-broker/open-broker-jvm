package org.openbroker.se.mortgage.events

import org.openbroker.se.model.BankAccount
import org.openbroker.common.model.Reference

data class OfferAccepted constructor(
    override val brokerReference: Reference,
    val bankAccount: BankAccount? = null
): MortgageEvent