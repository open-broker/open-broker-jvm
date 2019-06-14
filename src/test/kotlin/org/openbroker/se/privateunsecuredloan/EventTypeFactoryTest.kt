package org.openbroker.se.privateunsecuredloan

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.openbroker.se.privateunsecuredloan.events.PrivateUnsecuredLoanEvent

class EventTypeFactoryTest {

    @Test
    fun testCorrectSortingByComparator() {
        TestObjectsKotlin.Scenario1.apply {
            val events: List<PrivateUnsecuredLoanEvent> = sequenceOf(
                contractSent,
                delayedProcessing,
                contractSigned,
                applicationCreated,
                disbursed,
                offering,
                offerAccepted
            )
                .map { it.data!! }
                .toList()
                .sortedWith(PrivateUnsecuredLoanSweden)

            assertEquals(applicationCreated.data!!, events[0])
            assertEquals(delayedProcessing.data!!, events[1])
            assertEquals(offering.data!!, events[2])
            assertEquals(offerAccepted.data!!, events[3])
            assertEquals(contractSent.data!!, events[4])
            assertEquals(contractSigned.data!!, events[5])
            assertEquals(disbursed.data!!, events[6])
        }
    }

    @Test
    fun testCorrectSortingByComparatorWithStatusUpdatedOutOfOrder() {
        TestObjectsKotlin.Scenario1.apply {
            val events: List<PrivateUnsecuredLoanEvent> = sequenceOf(
                delayedProcessing,
                contractSigned,
                applicationCreated,
                contractSent,
                disbursed,
                offering,
                offerAccepted
            )
                .map { it.data!! }
                .toList()
                .sortedWith(PrivateUnsecuredLoanSweden)

            assertEquals(applicationCreated.data!!, events[0])
            assertEquals(delayedProcessing.data!!, events[1])
            assertEquals(offering.data!!, events[2])
            assertEquals(offerAccepted.data!!, events[3])
            assertEquals(contractSent.data!!, events[4])
            assertEquals(contractSigned.data!!, events[5])
            assertEquals(disbursed.data!!, events[6])
        }
    }
}