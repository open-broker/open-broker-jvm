package org.openbroker.se.mortgage.model

data class PropertyAddress(
    val streetAddress: String,
    val city: String,
    val postalCode: String,
    val apartmentCode: String
) {
    init {
    	require(streetAddress.isNotBlank()) { "streetAddress cannot be blank" }
        require(city.isNotBlank()) { "city cannot be blank" }
        require(postalCode.matches(postalCodeRegex))  { "postalCode does not match a regex for postal code" }
        require(apartmentCode.matches(apartmentCodeRegex)) { "apartmentCode does not match a regex for apartment code" }
    }

    companion object {
    	val postalCodeRegex = Regex("^\\d{5}$")
        val apartmentCodeRegex = Regex("^\\d{4}$")
    }
}
