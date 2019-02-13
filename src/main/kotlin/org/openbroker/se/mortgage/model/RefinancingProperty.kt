package org.openbroker.se.mortgage.model

import org.openbroker.common.requireMin

data class RefinancingProperty(
    val propertyAddress: PropertyAddress,
    val propertyType: PropertyType,
    val assessedValue: Int,
    val squareMeters: Int,
    val existingMortgage: Int,
    val monthlyCost: Int,
    val ownershipShare: Int,
    val rooms: Int? = null,
    val balcony: Boolean? = null,
    val elevator: Boolean? = null
) {
    init {
        assessedValue.requireMin(0, "assessedValue")
        squareMeters.requireMin(0, "squareMeters")
        existingMortgage.requireMin(0, "existingMortgage")
        monthlyCost.requireMin(0, "monthlyCost")
        rooms.requireMin(1, "rooms")
        require(ownershipShare in 1..100) {
            "Value for ownershipShare must be 1 <= 100, was $ownershipShare"
        }
    }
}