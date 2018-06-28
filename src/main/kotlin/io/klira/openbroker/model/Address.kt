package io.klira.openbroker.model

data class Address(
    val firstName: String,
    val lastName: String,
    val address: String,
    val city: String,
    val postalCode: String,
    val careOf: String? = null
) {
    init {
    	require(firstName.isNotBlank())
    	require(lastName.isNotBlank())
    	require(address.isNotBlank())
    	require(city.isNotBlank())
    	require(postalCode.isNotBlank())
    	careOf?.let { require(it.isNotBlank()) }
    }
}
