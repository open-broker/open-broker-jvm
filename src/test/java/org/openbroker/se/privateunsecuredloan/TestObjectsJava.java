package org.openbroker.se.privateunsecuredloan;

import org.openbroker.cloudevents.CloudEvent;
import org.openbroker.common.OpenBrokerEventKt;
import org.openbroker.common.model.AmortizationType;
import org.openbroker.common.model.DataProtectionContext;
import org.openbroker.common.model.Reference;
import org.openbroker.se.model.Address;
import org.openbroker.se.model.EmploymentStatus;
import org.openbroker.se.model.HousingType;
import org.openbroker.se.model.MaritalStatus;
import org.openbroker.se.privateunsecuredloan.events.*;
import org.openbroker.se.privateunsecuredloan.model.*;

import java.util.ArrayList;

class TestObjectsJava {
    private static Reference reference = new Reference("S1", "io.klira");
    private static String source = "klira.io";

    static CloudEvent<ApplicationCreated> applicationCreated = OpenBrokerEventKt.create(
        new ApplicationCreated(
            new Application(
                new Applicant(
                    "198012311234",
                null,
                new ArrayList<>(),
                null,
                EmploymentStatus.EARLY_RETIRED,
                2010,
                12,
                0,
                HousingType.LIVE_IN,
                10000,
                null,
                null,
                30000,
                null,
                null,
                MaritalStatus.COHABITING,
                false,
                null,
                new ArrayList<String>(){{ add("SE"); }},
                    new ArrayList<String>(){{ add("SE"); add("FI"); }},
                    new ArrayList<String>(){{ add("SE"); add("US"); }},
                new Address("Jane", "Doe", "Exempelstigen 4", "Gräsdalen", "12345", null)
                ),
                15000,
                12
            ),
            reference,
            DataProtectionContext.FICTIONAL
        ),
        ApplicationCreated.class,
        source
    );

    static CloudEvent<DelayedProcessing> delayedProcessing = OpenBrokerEventKt.create(
        new DelayedProcessing(
            reference,
            DelayReason.MANUAL_PROCESSING
        ),
        DelayedProcessing.class,
        source
    );

    static CloudEvent<Rejection> rejection = OpenBrokerEventKt.create(
        new Rejection(
            reference
        ),
        Rejection.class,
        source
    );

    static CloudEvent<Offering> offering = OpenBrokerEventKt.create(
        new Offering(
            reference,
            new Offer(
            "14.5",
            "12.1",
            150_000,
            150_000,
            160_000,
            2312,
            2000,
            150,
            20,
            10,
            56,
            AmortizationType.ANNUITY,
            "Hello World!"
            ),
            null
        ),
        Offering.class,
        source
    );

    static CloudEvent<OfferRejected> offerRejected = OpenBrokerEventKt.create(
        new OfferRejected(
            reference
        ),
        OfferRejected.class,
        source
    );

    static CloudEvent<OfferAccepted> offerAccepted = OpenBrokerEventKt.create(
        new OfferAccepted(
            reference
        ),
        OfferAccepted.class,
        source
    );

    static  CloudEvent<StatusUpdated> statusUpdated = OpenBrokerEventKt.create(
        new StatusUpdated(
            reference,
            Status.CONTRACT_SENT_TO_CUSTOMER
        ),
        StatusUpdated.class,
        source
    );

    static CloudEvent<Disbursed> disbursed = OpenBrokerEventKt.create(
        new Disbursed(
            reference,
            240_000,
            240_000
        ),
        Disbursed.class,
        source
    );

}
