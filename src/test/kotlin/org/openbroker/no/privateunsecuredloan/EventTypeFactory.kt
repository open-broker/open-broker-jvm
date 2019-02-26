package org.openbroker.no.privateunsecuredloan

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.openbroker.no.privateunsecuredloan.events.PrivateUnsecuredLoanEvent

class EventTypeFactory {

    @Test
    fun testCorrectSortingByComparatorWithStatusUpdatedOutOfOrder() {
        TestObjectsKotlin.Scenario1.apply {
            val events: List<PrivateUnsecuredLoanEvent> = sequenceOf(
                rejection,
                contractSigned,
                applicationCreated,
                message,
                offerRejected,
                disbursed,
                offering,
                offerAccepted
            )
                .map { it.data!! }
                .toList()
                .sortedWith(PrivateUnsecuredLoanNorway)

            assertEquals(applicationCreated.data!!, events[0])
            assertEquals(message.data!!, events[1])
            assertEquals(rejection.data!!, events[2])
            assertEquals(offering.data!!, events[3])
            assertEquals(offerRejected.data!!, events[4])
            assertEquals(offerAccepted.data!!, events[5])
            assertEquals(contractSigned.data!!, events[6])
            assertEquals(disbursed.data!!, events[7])
        }
    }

    @Test
    fun testCorrectSortingByComparator() {
        TestObjectsKotlin.Scenario2.apply {
            val events: List<PrivateUnsecuredLoanEvent> = sequenceOf(
                contractSigned,
                message1,
                offerAccepted,
                applicationCreated,
                disbursed,
                offering
            )
                .map { it.data!! }
                .toList()
                .sortedWith(PrivateUnsecuredLoanNorway)

            assertEquals(applicationCreated.data!!, events[0])
            assertEquals(message1.data!!, events[1])
            assertEquals(offering.data!!, events[2])
            assertEquals(offerAccepted.data!!, events[3])
            assertEquals(contractSigned.data!!, events[4])
            assertEquals(disbursed.data!!, events[5])
        }
    }
}