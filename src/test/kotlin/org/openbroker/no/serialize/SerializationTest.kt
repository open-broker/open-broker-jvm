package org.openbroker.no.serialize

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.openbroker.cloudevents.CloudEvent
import org.openbroker.cloudevents.cloudEvent
import org.openbroker.cloudevents.jsonString
import org.openbroker.common.OpenBrokerEvent
import org.openbroker.common.model.Address
import org.openbroker.common.model.DataProtectionContext
import org.openbroker.common.model.Reference
import org.openbroker.common.openBrokerEvent
import org.openbroker.common.serialize.parseOpenBrokerEvent
import org.openbroker.no.model.EmploymentStatus
import org.openbroker.no.model.HousingType
import org.openbroker.no.model.MaritalStatus
import org.openbroker.no.privateunsecuredloan.EventTypeFactory
import org.openbroker.no.privateunsecuredloan.TestObjectsJson
import org.openbroker.no.privateunsecuredloan.events.ApplicationCreated
import org.openbroker.no.privateunsecuredloan.events.ContractSigned
import org.openbroker.no.privateunsecuredloan.events.Message
import org.openbroker.no.privateunsecuredloan.events.Offering
import org.openbroker.no.privateunsecuredloan.model.*

class SerializationTest {

    @Test
    fun testSerializeOpenBrokerApplicationCreated() {
        val applicant = Applicant(
            ssn = "31128012345",
            phone = null,
            secondaryPhone = emptyList(),
            employmentStatus = EmploymentStatus.PUBLIC_SECTOR,
            employmentStatusSinceYear = 2010,
            employmentStatusSinceMonth = 12,
            employerName = "Company Inc",
            employerPhone = null,
            dependentChildren = 0,
            childSupportReceivedMonthly = null,
            rentReceivedMonthly = null,
            otherIncomeReceivedMonthly = 4000,
            childSupportPaidMonthly = null,
            paymentRemark = false,
            housingType = HousingType.LODGER,
            housingSinceYear = 2010,
            housingSinceMonth = 1,
            housingCostPerMonth = 10000,
            monthlyIncome = 30000,
            yearlyIncome = 468000,
            partnerYearlyIncome = 500000,
            maritalStatus = MaritalStatus.COHABITING,
            bankAccount = null,
            citizenships = listOf("NO", "FI"),
            livedInCountrySinceYear = 1980,
            countriesOfResidence = listOf("NO"),
            taxResidentOf = listOf("NO", "US"),
            education = Education.UNIVERSITY_LONG,
            tentativeAddress = Address(
                "Jane",
                "Doe",
                "Exempelstigen 4",
                "Gräsdalen",
                "12345"
            )
        )
        val app = Application(
            applicant = applicant,
            existingLoans = listOf(
                ExistingLoan(4000, 22, 2000, ExistingLoanType.CAR_LOAN, Responsibility.SHARED, "Example Bank"),
                ExistingLoan(15_000, 56, 0, ExistingLoanType.STUDENT_LOAN, Responsibility.MAIN_APPLICANT, "Other")
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
            eventType = "org.open-broker.v0.no.PrivateUnsecuredLoanApplicationCreated",
            eventTypeVersion = "v0",
            source = "https://some-domain.io"
        )

        assertNotNull(jsonEvent.data)
        assertTrue(jsonEvent.data!!.toString().isNotEmpty())
    }

    @Test
    fun testDeserializeOpenBrokerApplicationCreatedToType() {
        val event: CloudEvent<ApplicationCreated> = cloudEvent(TestObjectsJson.fullApplicationCreatedJson)
        assertNotNull(event.data)
        val applicationCreated: ApplicationCreated = event.data!!
        assertEquals("1", applicationCreated.brokerReference.id)
        assertEquals("Doe", applicationCreated.application.applicant.tentativeAddress?.lastName)
    }

    @Test
    fun testDeserializeOpenBrokerApplicationCreatedWithCoApplicantToType() {
        val event: CloudEvent<ApplicationCreated> = cloudEvent(TestObjectsJson.fullApplicationCreatedWithCoApplicantJson)
        assertNotNull(event.data)
        val applicationCreated: ApplicationCreated = event.data!!
        assertEquals("1", applicationCreated.brokerReference.id)
        assertEquals("Doe", applicationCreated.application.coApplicant?.tentativeAddress?.lastName)
        assertEquals(0, applicationCreated.application.coApplicant?.dependentChildren)
    }

    @Test
    fun testDeserializeOpenBrokerLoanOffering() {
        val event: CloudEvent<Offering> = cloudEvent(TestObjectsJson.loanOffering1)
        assertNotNull(event.data)
        val offer: Offer = event.data!!.offer
        assertEquals(65_000, offer.minOfferedCredit)
        assertEquals(67_000, offer.offeredCredit)
        assertEquals(70_000, offer.maxOfferedCredit)
        assertEquals(AmortizationType.ANNUITY, offer.amortizationType)
    }

    @Test
    fun testSerializeAndDeserializeOpenBrokerEvent() {
        val message = Message(Reference("1", "org"), message = "Hello World", requiresAction = false)
        val originalEvent: CloudEvent<Message> = openBrokerEvent(event = message, source = "org.something")
        val serializedEvent: String = jsonString(originalEvent)
        val deserializedEvent: CloudEvent<Message> = cloudEvent(serializedEvent)
        assertEquals(originalEvent, deserializedEvent)
    }

    @Test
    fun testSerializeAndDeserializeUnknownOpenBrokerEvent() {
        val contractSigned = ContractSigned(Reference("1", "org"))
        val originalEvent: CloudEvent<ContractSigned> = openBrokerEvent(event = contractSigned, source = "org.something")
        val serializedEvent: String = jsonString(originalEvent)
        val deserializedEvent: CloudEvent<out OpenBrokerEvent> = parseOpenBrokerEvent(serializedEvent)
        val castedEvent: CloudEvent<ContractSigned> = deserializedEvent as CloudEvent<ContractSigned>
        assertEquals(originalEvent, castedEvent)
    }
}