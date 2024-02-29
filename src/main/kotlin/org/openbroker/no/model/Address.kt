package org.openbroker.no.model

import org.openbroker.common.requireMatchRegex
import org.openbroker.common.requireNotBlank

data class Address(
    val firstName: String,
    val lastName: String,
    val address: String? = null,
    val city: String? = null,
    val postalCode: String? = null,
    val careOf: String? = null
) {
    init {
        firstName.requireNotBlank("firstName")
        lastName.requireNotBlank("lastName")
        postalCode?.requireMatchRegex(postalCodeRegex, "postalCode")
        careOf?.requireNotBlank("careOf")
    }

    companion object {
        val postalCodeRegex = Regex("^(\\d{4})?$")
    }
}