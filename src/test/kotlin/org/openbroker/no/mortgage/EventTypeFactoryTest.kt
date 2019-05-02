package org.openbroker.no.mortgage

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.openbroker.cloudevents.CloudEvent
import org.openbroker.no.mortgage.events.ApplicationCreated
import org.openbroker.no.mortgage.events.ApplicationRejection
import org.openbroker.no.mortgage.events.Disbursed
import org.openbroker.no.mortgage.events.Message
import org.openbroker.no.mortgage.events.MortgageEvent
import org.openbroker.no.mortgage.events.OfferAccepted
import org.openbroker.no.mortgage.events.OfferRejected
import org.openbroker.no.mortgage.events.Offering
import org.openbroker.parseJsonFromFile

class EventTypeFactoryTest {
    private val applicationCreated: CloudEvent<ApplicationCreated> = parseJsonFromFile("no/mortgage/MortgageApplicationCreated")
    private val offering: CloudEvent<Offering> = parseJsonFromFile("no/mortgage/MortgageApplicationOffering")
    private val applicationRejection: CloudEvent<ApplicationRejection> = parseJsonFromFile("no/mortgage/MortgageApplicationRejection")
    private val disbursed: CloudEvent<Disbursed> = parseJsonFromFile("no/mortgage/MortgageDisbursed")
    private val message: CloudEvent<Message> = parseJsonFromFile("no/mortgage/MortgageMessage")
    private val offerAccepted: CloudEvent<OfferAccepted> = parseJsonFromFile("no/mortgage/MortgageOfferAccepted")
    private val offerRejected: CloudEvent<OfferRejected> = parseJsonFromFile("no/mortgage/MortgageOfferRejected")

    /**
     * This test makes sure that
     * 1. Parsing JSON examples files (or JSON String representation in general)
     * to the JVM class counterpart works as intended
     * 2. That the [Comparator] in [MortgageNorway] works as intended
     */
    @Test
    fun testCorrectSortingByComparator() {
        val events: List<MortgageEvent> = sequenceOf(
            applicationCreated,
            offering,
            applicationRejection,
            disbursed,
            message,
            offerAccepted,
            offerRejected
        )
            .map { it.data!! }
            .toList()
            .sortedWith(MortgageNorway)

        assertEquals(applicationCreated.data!!, events[0])
        assertEquals(offering.data!!, events[1])
        assertEquals(applicationRejection.data!!, events[2])
        assertEquals(offerRejected.data!!, events[3])
        assertEquals(offerAccepted.data!!, events[4])
        assertEquals(disbursed.data!!, events[5])
        assertEquals(message.data!!, events[6])
    }
}