package io.klira.openbroker

internal fun Int?.requireMin(min: Int, propertyName: String? = null) {
    if(this == null)
        return
    require(this >= min) {
        val descriptor: String = propertyName?.let { " '$it'" } ?: ""
        "Value$descriptor must be at least $min, but was $this"
    }
}