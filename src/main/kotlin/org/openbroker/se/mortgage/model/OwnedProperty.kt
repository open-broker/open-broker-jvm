package org.openbroker.se.mortgage.model

import org.openbroker.common.requireMin

data class OwnedProperty(
    val propertyType: PropertyType,
    val monthlyCost: Int,
    val ownershipShare: Int
) {
    init {
        monthlyCost.requireMin(0, "monthlyCost")
        require(ownershipShare in 1..100) {
            "Value for ownershipShare must be 1 <= 100, was $ownershipShare"
        }
    }
}