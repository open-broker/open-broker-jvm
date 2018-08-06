package org.openbroker.serialize;

import org.junit.jupiter.api.Test;
import org.openbroker.TestObjects;
import org.openbroker.cloudevents.CloudEvent;
import org.openbroker.cloudevents.SerializationKt;
import org.openbroker.events.ApplicationCreated;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SerializationTestJava {
    @Test
    public void testDeserializeOpenBrokerApplicationCreatedToType() {
        CloudEvent<ApplicationCreated> event = SerializationKt.cloudEvent(TestObjects.fullApplicationCreatedJson, ApplicationCreated.class);
        assertNotNull(event.getData());
        ApplicationCreated app = event.getData();
        assertEquals(app.getApplication().getApplicant().getTentativeAddress().getFirstName(), "Christin");
    }
}
