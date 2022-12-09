package org.openbroker.no.mortgage.model

data class PropertyAddress(
    val streetAddress: String,
    val city: String,
    val postalCode: String
) {
    init {
        require(streetAddress.isNotBlank()) { "streetAddress cannot be blank" }
        require(city.isNotBlank()) { "city cannot be blank" }
        require(postalCode.matches(postalCodeRegex)) { "postalCode does not match a regex for postal code" }
    }

    companion object {
        val postalCodeRegex = Regex("^\\d{4}$")
    }
}
