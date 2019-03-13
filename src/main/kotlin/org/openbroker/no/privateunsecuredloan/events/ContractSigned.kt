package org.openbroker.no.privateunsecuredloan.events

import org.openbroker.common.model.Reference

/**
 * An event indicating that the bank has received a signed contract from the customer
 */
data class ContractSigned(override val brokerReference: Reference): PrivateUnsecuredLoanEvent