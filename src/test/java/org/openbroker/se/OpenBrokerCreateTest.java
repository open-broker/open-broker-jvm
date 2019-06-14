package org.openbroker.se;

import org.junit.jupiter.api.Test;
import org.openbroker.cloudevents.CloudEvent;
import org.openbroker.common.OpenBrokerEventKt;
import org.openbroker.se.model.BankAccount;
import org.openbroker.common.model.Reference;
import org.openbroker.se.privateunsecuredloan.events.OfferAccepted;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OpenBrokerCreateTest {

    @Test
    void testCreateOpenBrokerEvent() {
        CloudEvent<OfferAccepted> accept = OpenBrokerEventKt.create(
            new OfferAccepted(new Reference("1", "io.klira"), new BankAccount("3300", "1234567890")),
            OfferAccepted.class,
            "source"
        );

        assertEquals("3300", accept.getData().getBankAccount().getClearingNo());
    }
}
