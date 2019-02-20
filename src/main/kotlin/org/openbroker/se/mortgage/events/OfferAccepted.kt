package org.openbroker.se.mortgage.events

import org.openbroker.common.model.BankAccount
import org.openbroker.common.model.Reference

data class OfferAccepted constructor(
    override val brokerReference: Reference,
    val bankAccount: BankAccount
): MortgageEvent