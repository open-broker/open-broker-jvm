package org.openbroker.se.mortgage

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.openbroker.se.mortgage.events.MortgageEvent

class EventTypeFactoryTest {

    @Test
    fun testCorrectSortingByComparator() {
        TestObjects.Scenario1.apply {
            val events: List<MortgageEvent> = sequenceOf(
                contractSent,
                contractSigned,
                applicationCreated1,
                disbursed,
                offering1,
                message,
                offerAccepted
            )
                .map { it.data!! }
                .toList()
                .sortedWith(MortgageSweden)

            assertEquals(applicationCreated1.data!!, events[0])
            assertEquals(offering1.data!!, events[1])
            assertEquals(offerAccepted.data!!, events[2])
            assertEquals(contractSent.data!!, events[3])
            assertEquals(contractSigned.data!!, events[4])
            assertEquals(disbursed.data!!, events[5])
            assertEquals(message.data!!, events[6])
        }
    }
}