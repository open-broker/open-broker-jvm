package org.openbroker.common;

import org.junit.jupiter.api.Test;
import org.openbroker.se.privateunsecuredloan.PrivateUnsecuredLoanSweden;

import static org.junit.jupiter.api.Assertions.*;

public class EventTypeTest {

    @Test
    public void switchOnEventTypeTest() {
        String eventTypeName = "org.open-broker.v0.se.PrivateUnsecuredLoanApplicationCreated";
        PrivateUnsecuredLoanSweden event = (PrivateUnsecuredLoanSweden) PrivateUnsecuredLoanSweden.Companion.invoke(eventTypeName);
        switch (event) {
            case APPLICATION_CREATED:
                assertEquals(PrivateUnsecuredLoanSweden.APPLICATION_CREATED.getClazz(), event.getClazz());
                assertEquals(PrivateUnsecuredLoanSweden.APPLICATION_CREATED.eventName().getFullName(), eventTypeName);
                return;
            case OFFERING: fail();
            case DELAYED_PROCESSING: fail();
            default: fail();
        }
    }
}
