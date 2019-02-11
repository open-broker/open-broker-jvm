package org.openbroker.mortgage.model

data class Application @JvmOverloads constructor(
    val applicant: Applicant,
    val coApplicant: Applicant? = null,
    val refinancingProperty: OwnedProperty,
    val propertyAddress: PropertyAddress,
    val ownedProperties: List<OwnedProperty> = emptyList(),
    val existingLoans: List<ExistingLoan> = emptyList(),
    val extensions: Map<String, Any> = emptyMap()
)