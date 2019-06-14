package org.openbroker.no.mortgage.model

import org.openbroker.common.requireMin

data class RefinancingProperty(
    val propertyAddress: PropertyAddress,
    val propertyType: PropertyType,
    val assessedValue: Int,
    val squareMeters: Int,
    val monthlyCost: Int,
    val condominiumCompoundDebt: Int? = null
) {
    init {
        assessedValue.requireMin(0, "assessedValue")
        squareMeters.requireMin(0, "squareMeters")
        monthlyCost.requireMin(0, "monthlyCost")
        condominiumCompoundDebt.requireMin(0, "condominiumCompoundDebt")
    }

    companion object {
        private val interestRateRegex = Regex("^\\d\\.\\d+\$")
    }
}