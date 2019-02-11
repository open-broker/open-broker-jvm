package org.openbroker.mortgage.events

import org.openbroker.common.model.Reference

data class ContractSigned(override val brokerReference: Reference): MortgageEvent