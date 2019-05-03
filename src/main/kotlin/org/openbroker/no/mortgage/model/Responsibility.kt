package org.openbroker.no.mortgage.model

enum class Responsibility {
    /**
     * The main applicant is responsible for this loan
     */
    MAIN_APPLICANT,

    /**
     * The co-applicant is responsible for this loan
     */
    CO_APPLICANT,

    /**
     * The loan is a shared responsibility for the applicants
     */
    SHARED
}