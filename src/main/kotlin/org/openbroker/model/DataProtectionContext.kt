package org.openbroker.model

/**
 * The data-protection context of the application
 *
 * Consumers MAY reject or refuse to process data sent in the REAL
 * data protection context if the consumer deems it cannot secure
 * the data being sent. Additionally consumers may reject FICTIONAL
 * data if processing may affect real persons, for example,
 * ordering a hard credit inquiry.
 */
enum class DataProtectionContext {
    /**
     * Fictional data, does not concern real data-subjects
     */
    FICTIONAL,
    /**
     * Production data consisting concerning real data-subjects
     */
    REAL
}