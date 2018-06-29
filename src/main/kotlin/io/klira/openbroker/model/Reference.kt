package io.klira.openbroker.model

internal val issuerRegex = Regex("^(([a-zA-Z0-9]|[a-zA-Z0-9][a-zA-Z0-9-]*[a-zA-Z0-9]).)+([A-Za-z0-9]|[A-Za-z0-9][A-Za-z0-9-]*[A-Za-z0-9])\$")

/**
 *  A reference used for identification of an event. Consisting of two
 *  parts an issuer domain name of the form `com.example`, and an
 *  arbitrary id string. The values, taken together must be unique.
 *  In other words, the issuing organization can issue an ID once only.
 */
data class Reference(
    /**
     * An arbitrary reference string that is unique
     */
    val id: String,

    /**
     * Reversed DNS name for the broker, for example, example.com becomes com.example
     */
    val issuer: String
) {
    init {
        require(issuer.matches(issuerRegex)) {
            "Issuer '$issuer' does not match regex for issuer"
        }
    }

    override fun toString(): String = "$issuer::$id"
}