package org.openbroker.se.mortgage.model

import org.openbroker.common.requireMatchRegex
import org.openbroker.common.requireNotBlank

data class PropertyAddress(
    val streetAddress: String,
    val city: String,
    val postalCode: String,
    val apartmentCode: String
) {
    init {
        streetAddress.requireNotBlank("streetAddress")
        city.requireNotBlank("city")
        postalCode.requireMatchRegex(postalCodeRegex, "postalCode")
        apartmentCode.requireMatchRegex(apartmentCodeRegex, "apartmentCode")
    }

    companion object {
    	val postalCodeRegex = Regex("^\\d{5}$")
        val apartmentCodeRegex = Regex("^\\d{4}$")
    }
}
