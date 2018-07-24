package org.openbroker.serialize

import org.openbroker.TestObjects
import org.openbroker.model.Address
import org.openbroker.model.Applicant
import org.openbroker.model.Application
import org.openbroker.events.ApplicationCreated
import org.openbroker.events.StatusUpdated
import org.openbroker.meta.EventTypePrivateUnsecuredLoan
import org.openbroker.model.BankAccount
import org.openbroker.model.DataProtectionContext
import org.openbroker.model.EmploymentStatus
import org.openbroker.model.ExistingLoan
import org.openbroker.model.ExistingLoanType
import org.openbroker.model.HousingType
import org.openbroker.model.MaritalStatus
import org.openbroker.model.Reference
import org.openbroker.model.Responsibility
import org.openbroker.model.Status
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.openbroker.cloudevents.CloudEvent
import org.openbroker.cloudevents.cloudEvent
import org.openbroker.cloudevents.jsonString
import org.openbroker.events.Offering
import org.openbroker.model.AmortizationType
import org.openbroker.model.Offer

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
}