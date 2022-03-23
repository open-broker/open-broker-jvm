package org.openbroker.no;

import org.junit.jupiter.api.Test;
import org.openbroker.cloudevents.CloudEvent;
import org.openbroker.common.OpenBrokerEventKt;
import org.openbroker.common.model.Reference;
import org.openbroker.no.privateunsecuredloan.events.OfferAccepted;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OpenBrokerCreateTest {

    @Test
    public void testCreateOpenBrokerEvent() {
        CloudEvent<OfferAccepted> accept = OpenBrokerEventKt.create(
            new OfferAccepted(
                new Reference("1", "io.klira"),
                new Reference("123", "com.creditor"),
                "12345678901",
                null,
                56
            ),
            OfferAccepted.class,
            "source"
        );

        assertEquals("12345678901", accept.getData().getBankAccount());
        assertEquals(56, Objects.requireNonNull(accept.getData().getTermMonths()).intValue());
    }
}
