package org.openbroker.se.privateunsecuredloan.model

enum class DelayReason {
    /**
     * The application is being processed manually
     */
    MANUAL_PROCESSING,

    /**
     * The application will be processed later because of a holiday
     */
    BANK_HOLIDAY,

    /**
     * The service provider is experiencing operational issues and processing will be delayed
     */
    OPERATIONAL_ISSUES,

    /**
     * The application was delayed for some other reason
     */
    OTHER
}