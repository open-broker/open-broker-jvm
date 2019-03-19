package org.openbroker.no.model

private val accountNumberRegex = Regex("^[\\d]{11}$")

/**
 * A Norwegian bank account consisting of an account number
 */
data class BankAccount(
    /**
     * The account number within the bank. Specified using digits only.
     */
    val accountNo: String
) {
    init {
        require(accountNo.matches(accountNumberRegex)) {
            "Invalid account number with format '${obfuscateDigits(accountNo)}'"
        }
    }

    private fun obfuscateDigits(number: String): String =
        number.replace(Regex("\\d"), "*")
}