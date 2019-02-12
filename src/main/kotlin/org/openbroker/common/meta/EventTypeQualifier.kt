package org.openbroker.common.meta

/**
 * Qualifier for an event. Properties used are version, region, domain
 * and namespace.
 */
data class EventTypeQualifier(
    val version: String,
    val region: String,
    val domain: String
) {
    companion object {
        const val NAME_SPACE: String = "org.open-broker"
    }

    override fun toString(): String = "$NAME_SPACE.$version.$region.$domain"
}