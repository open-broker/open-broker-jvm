package org.openbroker.no.model

enum class EmploymentStatus {
    /**
     * Managing the home without employment
     */
    DOMESTIC,
    /**
     * Retired earlier before the usual retirement age
     */
    EARLY_RETIRED,
    /**
     * Formally permanently employed, hired by the hour
     */
    HOURLY,
    /**
     * An employment status not falling neatly in to any of the other categories
     */
    OTHER,
    /**
     * Employed full-time in the private sector
     */
    PRIVATE_SECTOR,
    /**
     * Employed full-time in the public sector
     */
    PUBLIC_SECTOR,
    /**
     * Temporarily receiving financial support due to injury or disease
     */
    REHABILITATION,
    /**
     * Retired due to age
     */
    RETIRED,
    /**
     * The applicant is a sole proprietor or works for his or her own company
     */
    SELF_EMPLOYED,
    /**
     * Signed up to a university or other higher education
     */
    STUDENT,
    /**
     * Employment pertaining to a limited time period
     */
    TEMPORARY,
    /**
     * Trial employment
     */
    TRIAL,
    /**
     * Not currently employed
     */
    UNEMPLOYED
}