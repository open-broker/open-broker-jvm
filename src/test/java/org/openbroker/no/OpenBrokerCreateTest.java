package org.openbroker.no;

import org.junit.jupiter.api.Test;
import org.openbroker.cloudevents.CloudEvent;
import org.openbroker.common.OpenBrokerEventKt;
import org.openbroker.common.model.Reference;
import org.openbroker.no.privateunsecuredloan.events.OfferAccepted;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OpenBrokerCreateTest {

    @Test
    void testCreateOpenBrokerEvent() {
        CloudEvent<OfferAccepted> accept = OpenBrokerEventKt.create(
            new OfferAccepted(new Reference("1", "io.klira"), "12345678901"),
            OfferAccepted.class,
            "source"
        );

        assertEquals("12345678901", accept.getData().getBankAccount());
    }
}
