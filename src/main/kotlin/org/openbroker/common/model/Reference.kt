package org.openbroker.common.model

import java.nio.charset.Charset

internal val issuerRegex = Regex("^(([a-zA-Z0-9]|[a-zA-Z0-9][a-zA-Z0-9-]*[a-zA-Z0-9]).)+([A-Za-z0-9]|[A-Za-z0-9][A-Za-z0-9-]*[A-Za-z0-9])\$")

/**
 *  A reference used for identification of an event. Consisting of two
 *  parts an issuer domain name of the form `com.example`, and an
 *  arbitrary id string. The events, taken together must be unique.
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

        require(id.isNotBlank()) { "id in reference must not be blank" }
    }

    /**
     * @return a String representation of the Reference in the form
     * of `issuer::id`
     */
    override fun toString(): String = "$issuer::$id"

    /**
     * Base 64 encode the Reference. This method can be used when the reference
     * will be used in a context where it must be URL safe. It can be reversed
     * with [Reference.decode]
     *
     * @return the String representation of this Reference in form of a
     * URL safe base 64 encoded String which consists of both the [issuer]
     * and the [id], as create by [Reference.toString]
     */
    fun base64(): String {
        val input = toString().toByteArray(Charset.forName("UTF-8"))
        val encoded: ByteArray = base64Encode(input)
        return String(encoded)
    }

    /**
     * Transform a base 64 encoded String version of a Reference back to
     * its original form.
     */
    companion object {
    	fun decode(input: String): Reference {
            val bytes = base64Decode(input.toByteArray(Charset.forName("UTF-8")))
            String(bytes).split("::").let {
                require(it.size == 2) {
                    "Invalid input $input, could not be parsed as a reference"
                }
                return Reference(issuer = it[0], id = it[1])
            }
        }
    }
}

private fun base64Encode(bytes: ByteArray): ByteArray = java.util.Base64
    .getUrlEncoder()
    .withoutPadding()
    .encode(bytes)

private fun base64Decode(bytes: ByteArray): ByteArray = java.util.Base64
    .getUrlDecoder()
    .decode(bytes)