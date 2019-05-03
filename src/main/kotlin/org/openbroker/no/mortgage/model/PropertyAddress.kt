package org.openbroker.no.mortgage.model

data class PropertyAddress(
    val streetAddress: String,
    val city: String,
    val postalCode: String
) {
    init {
        require(streetAddress.isNotBlank())
        require(city.isNotBlank())
        require(postalCode.matches(postalCodeRegex))
    }

    companion object {
        val postalCodeRegex = Regex("^\\d{4}$")
    }
}
