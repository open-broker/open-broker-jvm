package org.openbroker.se;

import org.junit.jupiter.api.Test;
import org.openbroker.cloudevents.CloudEvent;
import org.openbroker.common.OpenBrokerEventKt;
import org.openbroker.se.model.BankAccount;
import org.openbroker.common.model.Reference;
import org.openbroker.se.privateunsecuredloan.events.OfferAccepted;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OpenBrokerCreateTest {

    @Test
    public void testCreateOpenBrokerEvent() {
        CloudEvent<OfferAccepted> accept = OpenBrokerEventKt.create(
            new OfferAccepted(
                new Reference("1", "io.klira"),
                new Reference("2", "com.creditor"),
                new BankAccount("3300", "1234567890", "Nordea"),
                null,
                84
            ),
            OfferAccepted.class,
            "source"
        );

        assertEquals("3300", accept.getData().getBankAccount().getClearingNo());
        assertEquals(84, Objects.requireNonNull(accept.getData().getTermMonths()).intValue());
    }
}
