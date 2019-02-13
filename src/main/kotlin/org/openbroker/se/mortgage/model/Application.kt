package org.openbroker.se.mortgage.model

data class Application @JvmOverloads constructor(
    val applicant: Applicant,
    val coApplicant: Applicant? = null,
    val refinancingProperty: RefinancingProperty,
    val ownedProperties: List<OwnedProperty> = emptyList(),
    val existingLoans: List<ExistingLoan> = emptyList(),
    val extensions: Map<String, Any> = emptyMap()
)