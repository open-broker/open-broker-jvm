package org.openbroker.no.mortgage.model

import org.openbroker.common.requireMatchRegex
import org.openbroker.common.requireNotBlank

data class PropertyAddress(
    val streetAddress: String,
    val city: String,
    val postalCode: String
) {
    init {
        streetAddress.requireNotBlank("streetAddress")
        city.requireNotBlank("city")
        postalCode.requireMatchRegex(postalCodeRegex, "postalCode")
    }

    companion object {
        val postalCodeRegex = Regex("^\\d{4}$")
    }
}
