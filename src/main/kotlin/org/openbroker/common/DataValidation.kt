package org.openbroker.common

internal fun Int?.requireMin(min: Int, propertyName: String? = null) {
    if(this == null)
        return
    require(this >= min) {
        val descriptor: String = propertyName?.let { " '$it'" } ?: ""
        "Value$descriptor must be at least $min, but was $this"
    }
}

internal fun Int?.requireInRange(min: Int, max: Int, propertyName: String) {
    require(this in min .. max) {
        "Invalid $propertyName: $this, should be in range $min-$max"
    }
}

internal fun Int.requireLessThanOrEqual(compareTo: Int, compareToPropertyName: String, propertyName: String) {
    require(this <= compareTo) {
        "Value for $propertyName: $this cannot be greater than value for $compareToPropertyName: $compareTo"
    }
}

internal fun String.requireNotBlank(propertyName: String) {
    require(this.isNotBlank()) {
        "$propertyName cannot be blank"
    }
}

internal fun String.requireNotEmpty(propertyName: String) {
    require(this.isNotEmpty()) {
        "$propertyName cannot be empty"
    }
}

internal fun String.requireMatchRegex(regex: Regex, propertyName: String) {
    require(this.matches(regex)) {
        "Invalid $propertyName: $this, does not match regex"
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
        "Invalid element in $propertyName: ${notValidElements.joinToString { it }}, does not match regex"
    }
}

internal fun <T> List<T>.requireNotEmpty(propertyName: String) {
    require(this.isNotEmpty()) {
        "$propertyName cannot be empty"
    }
}