package org.openbroker.no.model

enum class HousingType {
    /**
     * The applicant owns a part in a housing cooperative
     */
    COOPERATIVE,
    /**
     * The applicant rents accommodation in another's house
     */
    LODGER,
    /**
     * A housing situation not falling neatly in to any of the other categories
     */
    OTHER,
    /**
     * The applicant owns his or her apartment
     */
    OWN_APARTMENT,
    /**
     * The applicant owns his or her house
     */
    OWN_HOUSE,
    /**
     * The applicant is living with his or her parents
     */
    PARENTS,
    /**
     * The applicant rents his or her place of residence
     */
    RENTED
}