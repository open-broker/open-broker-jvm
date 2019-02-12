package org.openbroker.common.serialize

import org.openbroker.se.privateunsecuredloan.TestObjects
import org.openbroker.se.privateunsecuredloan.events.ApplicationCreated
import org.openbroker.se.privateunsecuredloan.events.StatusUpdated
import org.openbroker.se.privateunsecuredloan.meta.EventTypePrivateUnsecuredLoan
import org.openbroker.common.model.BankAccount
import org.openbroker.common.model.DataProtectionContext
import org.openbroker.common.model.EmploymentStatus
import org.openbroker.se.privateunsecuredloan.model.ExistingLoan
import org.openbroker.se.privateunsecuredloan.model.ExistingLoanType
import org.openbroker.common.model.HousingType
import org.openbroker.common.model.MaritalStatus
import org.openbroker.common.model.Reference
import org.openbroker.se.privateunsecuredloan.model.Responsibility
import org.openbroker.se.privateunsecuredloan.model.Status
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.openbroker.cloudevents.CloudEvent
import org.openbroker.cloudevents.cloudEvent
import org.openbroker.cloudevents.jsonString
import org.openbroker.common.OpenBrokerEvent
import org.openbroker.common.model.Address
import org.openbroker.se.privateunsecuredloan.events.Offering
import org.openbroker.se.privateunsecuredloan.model.AmortizationType
import org.openbroker.se.privateunsecuredloan.model.Applicant
import org.openbroker.se.privateunsecuredloan.model.Application
import org.openbroker.se.privateunsecuredloan.model.Offer

class SerializationTest {

    @Test
    fun testSerializeOpenBrokerApplicationCreated() {
        val applicant = Applicant(
            ssn = "198902071312",
            phone = "+46735711057",
            employmentStatus = EmploymentStatus.FULL_TIME,
            employmentStatusSinceYear = 2017,
            employmentStatusSinceMonth = 2,
            dependentChildren = 0,
            housingType = HousingType.RENTED,
            employerName = "Zensum AB",
            employerPhone = "+46811122233",
            monthlyIncome = 10_000,
            housingCostPerMonth = 3_000,
            maritalStatus = MaritalStatus.COHABITING,
            bankAccount = BankAccount("8888", "00000003"),
            citizenships = listOf("SE"),
            countriesOfResidence = listOf("SE"),
            paymentRemark = false,
            taxResidentOf = listOf("SE"),
            tentativeAddress = Address(
                firstName = "Christin",
                lastName = "Strömberg",
                address = "Hultom 264",
                postalCode = "25258",
                city = "Helsingborg"
            )
        )
        val app = Application(
            applicant = applicant,
            existingLoans = listOf(
                ExistingLoan(4000, 22, true, ExistingLoanType.CAR_LOAN, Responsibility.SHARED),
                ExistingLoan(15_000, 56, false, ExistingLoanType.STUDENT_LOAN, Responsibility.MAIN_APPLICANT)
            ),
            loanAmount = 20_000,
            termMonths = 24,
            extensions = mapOf("org.someExtensionProperty" to 42)
        )
        val appCreated = ApplicationCreated(
            application = app,
            brokerReference = Reference("1", "org"),
            dataProtectionContext = DataProtectionContext.FICTIONAL
        )

        val jsonEvent: CloudEvent<ApplicationCreated> = CloudEvent(
            data = appCreated,
            eventType = EventTypePrivateUnsecuredLoan.APPLICATION_CREATED.toString(),
            eventTypeVersion = "v0",
            source = "https://some-domain.io"
        )

        assertNotNull(jsonEvent.data)
        assertTrue(jsonEvent.data!!.toString().isNotEmpty())
    }

    @Test
    fun testDeserializeOpenBrokerApplicationCreatedToType() {
        val event: CloudEvent<ApplicationCreated> = cloudEvent(TestObjects.fullApplicationCreatedJson)
        assertNotNull(event.data)
        val applicationCreated: ApplicationCreated = event.data!!
        assertEquals("1", applicationCreated.brokerReference.id)
        assertEquals("Strömberg", applicationCreated.application.applicant.tentativeAddress?.lastName)
    }

    @Test
    fun testDeserializeOpenBrokerApplicationCreatedWithCoApplicantToType() {
        val event: CloudEvent<ApplicationCreated> = cloudEvent(TestObjects.fullApplicationCreatedWitCoApplicantJson)
        assertNotNull(event.data)
        val applicationCreated: ApplicationCreated = event.data!!
        assertEquals("1", applicationCreated.brokerReference.id)
        assertEquals("Albinsson", applicationCreated.application.coApplicant?.tentativeAddress?.lastName)
        assertEquals(15, applicationCreated.application.coApplicant?.dependentChildren)
    }

    @Test
    fun testDeserializeOpenBrokerLoanOffering() {
        val event: CloudEvent<Offering> = cloudEvent(TestObjects.loanOffering1)
        assertNotNull(event.data)
        val offer: Offer = event.data!!.offer
        assertEquals(65_000, offer.minOfferedCredit)
        assertEquals(67_000, offer.offeredCredit)
        assertEquals(70_000, offer.maxOfferedCredit)
        assertEquals(AmortizationType.ANNUITY, offer.amortizationType)
    }

    @Test
    fun testSerializeAndDeserializeOpenBrokerEvent() {
        val updated = StatusUpdated(Reference("1", "org"), Status.CONTRACT_SIGNED)
        val originalEvent: CloudEvent<StatusUpdated> = openBrokerEvent(event = updated, source = "org.something")
        val serialized: String = jsonString(originalEvent)
        val deserializedEvent: CloudEvent<StatusUpdated> = cloudEvent(serialized)

        assertEquals(originalEvent, deserializedEvent)
    }

    @Test
    fun testSerializeAndDeserializeUnknownOpenBrokerEvent() {
        val updated = StatusUpdated(Reference("1", "org"), Status.CONTRACT_SIGNED)
        val originalEvent: CloudEvent<StatusUpdated> = openBrokerEvent(event = updated, source = "org.something")
        val serialized: String = jsonString(originalEvent)
        val deserializedEvent: CloudEvent<out OpenBrokerEvent> = parseOpenBrokerEvent(serialized)
        val castedEvent: CloudEvent<StatusUpdated> = deserializedEvent as CloudEvent<StatusUpdated>
        assertEquals(originalEvent, castedEvent)
    }
}