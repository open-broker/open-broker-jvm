package org.openbroker.se.mortgage.model

import org.openbroker.common.requireInRange
import org.openbroker.common.requireMin

data class OwnedProperty(
    val propertyType: PropertyType,
    val existingMortgage: Int,
    val monthlyCost: Int,
    val ownershipShare: Int
) {
    init {
        existingMortgage.requireMin(0, "existingMortgage")
        monthlyCost.requireMin(0, "monthlyCost")
        ownershipShare.requireInRange(1, 100, "ownershipShare")
    }
}