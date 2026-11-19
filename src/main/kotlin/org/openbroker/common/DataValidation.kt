package org.openbroker.common

import org.openbroker.cloudevents.validateEventTime

internal fun Int?.requireMin(min: Int, propertyName: String? = null) {
    if(this == null)
        return
    require(this >= min) {
        val descriptor: String = propertyName?.let { " '$it'" } ?: ""
        composeMessage("Value$descriptor must be at least $min. $propertyName: $this.")
    }
}

internal fun Int.requireInRange(min: Int, max: Int, propertyName: String) {
    require(this in min .. max) {
        composeMessage("Invalid $propertyName, should be in range $min-$max. $propertyName: $this.")
    }
}

internal fun Int.requireLessThanOrEqual(compareTo: Int, compareToPropertyName: String, propertyName: String) {
    require(this <= compareTo) {
        composeMessage("Value for $propertyName cannot be greater than value for $compareToPropertyName. $propertyName: $this, $compareToPropertyName: $compareTo.")
    }
}

internal fun String.requireNotBlank(propertyName: String) {
    require(this.isNotBlank()) {
        composeMessage("$propertyName cannot be blank.")
    }
}

internal fun String.requireNotEmpty(propertyName: String) {
    require(this.isNotEmpty()) {
        composeMessage("$propertyName cannot be empty.")
    }
}

internal fun String.requireMatchRegex(regex: Regex, propertyName: String) {
    require(this.matches(regex)) {
        composeMessage("Invalid $propertyName value, does not match regex. $propertyName: $this.")
    }
}

// RFC 3339 allows lowercase 't' and 'z'; validateEventTime does not.
internal fun String.requireDateTime(propertyName: String) {
    require(runCatching { validateEventTime(this.toUpperCase()) }.isSuccess) {
        composeMessage("Invalid $propertyName value, must be an RFC 3339 date-time with seconds. $propertyName: $this.")
    }
}

internal fun List<String>.requireAllMatchRegex(regex: Regex, propertyName: String) {
    var allValid = true
    val notValidElements: MutableList<String> = mutableListOf()
    this.forEach {
        if (!it.matches(regex)) {
            allValid = false
            notValidElements.add(it)
        }
    }
    require(allValid) {
        composeMessage("Invalid element in $propertyName, does not match regex. ${notValidElements.joinToString { "$propertyName: $it" }}.")
    }
}

internal fun <T> List<T>.requireNotEmpty(propertyName: String) {
    require(this.isNotEmpty()) {
        composeMessage("$propertyName cannot be empty.")
    }
}

private fun composeMessage(message: String): String {
    return "{\"message\": \"$message\"}"
}