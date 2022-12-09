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
        "Invalid ${propertyName}: ${this}, should be in range ${min}-${max}"
    }
}