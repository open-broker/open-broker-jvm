package org.openbroker.se.mortgage.model

import org.openbroker.common.requireInRange
import org.openbroker.common.requireMin

data class RefinancingProperty(
    val propertyAddress: PropertyAddress,
    val propertyType: PropertyType,
    val interestRate: String,
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
        require(interestRate.matches(interestRateRegex)) {
            "Value interestRate has invalid format: '$interestRate'"
        }
        ownershipShare.requireInRange(1, 100, "ownershipShare")
    }

    companion object {
        private val interestRateRegex = Regex("^\\d\\.\\d+\$")
    }
}