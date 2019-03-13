package org.openbroker.se.privateunsecuredloan;

import org.junit.jupiter.api.Test;
import org.openbroker.cloudevents.CloudEvent;
import org.openbroker.se.privateunsecuredloan.events.PrivateUnsecuredLoanEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventTypeFactor {

    @Test
    void testCorrectSortingByComparatorWithStatusUpdatedOutOfOrder() {
        List<CloudEvent<?>> events = new ArrayList<>();
        events.add(TestObjectsJava.rejection);
        events.add(TestObjectsJava.applicationCreated);
        events.add(TestObjectsJava.offerRejected);
        events.add(TestObjectsJava.disbursed);
        events.add(TestObjectsJava.offering);
        events.add(TestObjectsJava.offerAccepted);
        events.add(TestObjectsJava.delayedProcessing);

        List<PrivateUnsecuredLoanEvent> sortedEvents = events.stream()
            .map(it -> (PrivateUnsecuredLoanEvent) it.getData())
            .sorted(PrivateUnsecuredLoanSweden.Companion)
            .collect(Collectors.toList());

        assertEquals(TestObjectsJava.applicationCreated.getData(), sortedEvents.get(0));
        assertEquals(TestObjectsJava.delayedProcessing.getData(), sortedEvents.get(1));
        assertEquals(TestObjectsJava.offering.getData(), sortedEvents.get(2));
        assertEquals(TestObjectsJava.rejection.getData(), sortedEvents.get(3));
        assertEquals(TestObjectsJava.offerAccepted.getData(), sortedEvents.get(4));
        assertEquals(TestObjectsJava.offerRejected.getData(), sortedEvents.get(5));
        assertEquals(TestObjectsJava.disbursed.getData(), sortedEvents.get(6));
    }

}
