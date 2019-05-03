package org.openbroker.no.mortgage.model

import org.openbroker.common.requireMin

data class RefinancingProperty(
    val propertyAddress: PropertyAddress,
    val propertyType: PropertyType,
    val interestRate: String,
    val assessedValue: Int,
    val squareMeters: Int,
    val existingMortgage: Int,
    val monthlyCost: Int,
    val condominiumCompoundDebt: Int? = null,
    val defaulted: Boolean
) {
    init {
        assessedValue.requireMin(0, "assessedValue")
        squareMeters.requireMin(0, "squareMeters")
        existingMortgage.requireMin(0, "existingMortgage")
        monthlyCost.requireMin(0, "monthlyCost")
        condominiumCompoundDebt.requireMin(0, "condominiumCompoundDebt")
        require(interestRate.matches(interestRateRegex)) {
            "Value interestRate has invalid format: '$interestRate'"
        }
    }

    companion object {
        private val interestRateRegex = Regex("^\\d\\.\\d+\$")
    }
}