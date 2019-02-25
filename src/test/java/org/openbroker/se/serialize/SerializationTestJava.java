package org.openbroker.se.serialize;

import org.junit.jupiter.api.Test;
import org.openbroker.cloudevents.CloudEvent;
import org.openbroker.se.privateunsecuredloan.TestObjectsJson;
import org.openbroker.cloudevents.SerializationKt;
import org.openbroker.se.privateunsecuredloan.events.ApplicationCreated;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SerializationTestJava {
    @Test
    public void testDeserializeOpenBrokerApplicationCreatedToType() {
        CloudEvent<ApplicationCreated> event = SerializationKt.cloudEvent(TestObjectsJson.fullApplicationCreatedJson, ApplicationCreated.class);
        assertNotNull(event.getData());
        ApplicationCreated app = event.getData();
        assertEquals(app.getApplication().getApplicant().getTentativeAddress().getFirstName(), "Christin");
    }
}
