package org.openbroker.se.mortgage.model

enum class PropertyType {
    /**
     * An ordinary house with a plot
     */
    HOUSE,
    /**
     * A house that is built adjacent to other houses
     */
    TERRACED_HOUSE,
    /**
     *  A house built for recreational living that may or may not
     *  be fit as a permanent resident
     */
    VACATION_HOME,
    /**
     *  An apartment that belongs to condominium compound
     */
    APARTMENT,
    /**
     * Some other form of real-estate property that does not belong
     * to any of the above categories
     */
    OTHER
}