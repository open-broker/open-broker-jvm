package org.openbroker.mortgage.model

data class PropertyAddress(
    val streetAddress: String,
    val city: String,
    val postalCode: String,
    val apartmentCode: String
) {
    init {
    	require(streetAddress.isNotBlank())
        require(city.isNotBlank())
        require(postalCode.matches(postalCodeRegex))
        require(apartmentCode.matches(apartmentCodeRegex))
    }

    companion object {
    	val postalCodeRegex = Regex("^\\d{5}$")
        val apartmentCodeRegex = Regex("^\\d{4}$")
    }
}
