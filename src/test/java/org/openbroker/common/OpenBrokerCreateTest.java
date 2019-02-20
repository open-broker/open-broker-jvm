package org.openbroker.common;

import org.junit.jupiter.api.Test;
import org.openbroker.cloudevents.CloudEvent;
import org.openbroker.common.model.BankAccount;
import org.openbroker.common.model.Reference;
import org.openbroker.se.privateunsecuredloan.events.OfferAccepted;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OpenBrokerCreateTest {

    @Test
    public void testCreateOpenBrokerEvent() {
        CloudEvent<OfferAccepted> accept = OpenBrokerEventKt.create(
            new OfferAccepted(new Reference("1", "io.klira"), new BankAccount("3300", "1234567890")),
            OfferAccepted.class,
            "source"
        );

        assertEquals("3300", accept.getData().getBankAccount().getClearingNo());
    }
}
