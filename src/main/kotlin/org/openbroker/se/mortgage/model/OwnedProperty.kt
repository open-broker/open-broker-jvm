package org.openbroker.se.mortgage.model

import org.openbroker.common.requireMin

data class OwnedProperty(
    val propertyType: PropertyType,
    val assessedValue: Int,
    val squareMeters: Int,
    val existingMortgage: Int,
    val monthlyCost: Int,
    val ownershipShare: Int
) {
    init {
    	assessedValue.requireMin(0, "assessedValue")
        squareMeters.requireMin(0, "squareMeters")
        existingMortgage.requireMin(0, "existingMortgage")
        monthlyCost.requireMin(0, "monthlyCost")
        require(ownershipShare in 1..100) {
            "Value for ownershipShare must be 1 <= 100, was $ownershipShare"
        }
    }
}