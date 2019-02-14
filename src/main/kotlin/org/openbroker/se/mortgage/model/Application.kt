package org.openbroker.se.mortgage.model

import org.openbroker.common.requireMin

data class Application @JvmOverloads constructor(
    val applicant: Applicant,
    val coApplicant: Applicant? = null,
    val refinancingProperty: RefinancingProperty,
    val adultsInHousehold: Int? = null,
    val ownedProperties: List<OwnedProperty> = emptyList(),
    val existingLoans: List<ExistingLoan> = emptyList(),
    val extensions: Map<String, Any> = emptyMap()
) {
    init {
        adultsInHousehold.requireMin(1, "adultsInHouseHold")
    }
}