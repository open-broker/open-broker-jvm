package org.openbroker.common

fun obfuscateDigits(number: String): String =
    number.replace(Regex("\\d"), "*")