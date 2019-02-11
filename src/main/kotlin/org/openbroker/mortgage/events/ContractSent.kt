package org.openbroker.mortgage.events

import org.openbroker.common.model.Reference

data class ContractSent(override val brokerReference: Reference): MortgageEvent