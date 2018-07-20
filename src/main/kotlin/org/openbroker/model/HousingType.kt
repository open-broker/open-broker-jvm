package org.openbroker.model

enum class HousingType {
    /**
     * The applicant doesn't directly rent or own a house but is rather a lodger
     */
    LIVE_IN,
    /**
     * The applicant rents his or her place of residence
     */
    RENTED,
    /**
     * The applicant owns his or her apartment
     */
    OWN_APARTMENT,
    /**
     * The applicant owns his or her house
     */
    OWN_HOUSE;
}