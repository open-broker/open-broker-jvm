package org.openbroker.se.model

enum class EmploymentStatus {
    /**
     * Retried earlier before the usual retirement age
     */
    EARLY_RETIRED,
    /**
     * Employed full-time
     */
    FULL_TIME,
    /**
     * Formally permanently employed, hired by the hour
     */
    HOURLY,
    /**
     * An employment status not falling neatly in to any of the other categories
     */
    OTHER,
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
     * Non-full time employment pertaining to a project
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