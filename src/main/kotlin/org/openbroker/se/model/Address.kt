package org.openbroker.se.model

data class Address(
    val firstName: String,
    val lastName: String,
    val address: String,
    val city: String,
    val postalCode: String,
    val careOf: String? = null
) {
    init {
        require(firstName.isNotBlank()) { "firstName cannot be blank" }
        require(lastName.isNotBlank()) { "lastName cannot be blank" }
        require(address.isNotBlank()) { "address cannot be blank" }
        require(city.isNotBlank()) { "city cannot be blank" }
        require(postalCode.matches(postalCodeRegex)) { "postalCode does not match a regex for postal code" }
        careOf?.let { require(it.isNotBlank()) { "careOf cannot be blank" } }
    }

    companion object {
        val postalCodeRegex = Regex("^\\d{3}\\s?\\d{2}$")
    }
}