package org.openbroker.no.serialize

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.openbroker.cloudevents.CloudEvent
import org.openbroker.cloudevents.cloudEvent
import org.openbroker.cloudevents.jsonString
import org.openbroker.common.OpenBrokerEvent
import org.openbroker.common.model.AmortizationType
import org.openbroker.common.model.DataProtectionContext
import org.openbroker.common.model.Origin
import org.openbroker.common.model.Reference
import org.openbroker.common.openBrokerEvent
import org.openbroker.common.serialize.parseOpenBrokerEvent
import org.openbroker.no.model.Address
import org.openbroker.no.model.EmploymentStatus
import org.openbroker.no.model.HousingType
import org.openbroker.no.model.MaritalStatus
import org.openbroker.no.privateunsecuredloan.TestObjectsJson
import org.openbroker.no.privateunsecuredloan.events.ApplicationCreated
import org.openbroker.no.privateunsecuredloan.events.ContractSigned
import org.openbroker.no.privateunsecuredloan.events.Message
import org.openbroker.no.privateunsecuredloan.events.OfferRejected
import org.openbroker.no.privateunsecuredloan.events.Offering
import org.openbroker.no.privateunsecuredloan.model.*

class SerializationTest {

    @Test
    fun testSerializeOpenBrokerApplicationCreated() {
        val applicant = Applicant(
            ssn = "31128012345",
            customerId = "abc145",
            phone = null,
            secondaryPhone = emptyList(),
            employmentStatus = EmploymentStatus.PUBLIC_SECTOR,
            employmentStatusSinceYear = 2010,
            employmentStatusSinceMonth = 12,
            employmentStatusUntilYear = null,
            employmentStatusUntilMonth = null,
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
            netMonthlyIncome = 30000,
            grossYearlyIncome = 468000,
            partnerGrossYearlyIncome = 500000,
            maritalStatus = MaritalStatus.COHABITING,
            bankAccount = "12355578901",
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
                "1234"
            )
        )
        val app = Application(
            applicant = applicant,
            existingLoans = listOf(
                ExistingLoan(
                    loanAmount = 4000,
                    monthlyPayment = 22,
                    refinanceAmount = 2000,
                    existingLoanType = ExistingLoanType.CAR_LOAN,
                    responsibility = Responsibility.SHARED,
                    lender = "Example Bank"
                ),
                ExistingLoan(
                    loanAmount = 15_000,
                    monthlyPayment = 56,
                    refinanceAmount = 0,
                    existingLoanType = ExistingLoanType.STUDENT_LOAN,
                    responsibility = Responsibility.MAIN_APPLICANT,
                    lender = "Other"
                )
            ),
            loanAmount = 20_000,
            termMonths = 24,
            extensions = mapOf("org.someExtensionProperty" to 42)
        )
        val appCreated = ApplicationCreated(
            application = app,
            brokerReference = Reference("1", "org.example"),
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
        assertNull(offer.condition)
    }

    @Test
    fun testDeserializeOpenBrokerLoanOfferingWithCondition() {
        val event: CloudEvent<Offering> = cloudEvent(TestObjectsJson.loanOfferingWithCondition)
        assertEquals(OfferCondition.CO_APPLICANT_NEEDED, event.data!!.offer.condition)
    }

    @Test
    fun testSerializeAndDeserializeOfferingWithCondition() {
        val offering = Offering(
            brokerReference = Reference("1", "org.example"),
            offer = Offer(
                offeredCredit = 50_000,
                arrangementFee = 0,
                termFee = 0,
                invoiceFee = 0,
                condition = OfferCondition.CO_APPLICANT_NEEDED
            )
        )
        val originalEvent: CloudEvent<Offering> = openBrokerEvent(event = offering, source = "org.something")
        val serializedEvent: String = jsonString(originalEvent)
        assertTrue(serializedEvent.contains("\"condition\":\"CO_APPLICANT_NEEDED\""))
        val deserializedEvent: CloudEvent<Offering> = cloudEvent(serializedEvent)
        assertEquals(originalEvent, deserializedEvent)
    }

    @Test
    fun testSerializeAndDeserializeOpenBrokerEvent() {
        val message = Message(Reference("1", "org.example"), message = "Hello World", requiresAction = false, origin = Origin.BANK)
        val originalEvent: CloudEvent<Message> = openBrokerEvent(event = message, source = "org.something")
        val serializedEvent: String = jsonString(originalEvent)
        val deserializedEvent: CloudEvent<Message> = cloudEvent(serializedEvent)
        assertEquals(originalEvent, deserializedEvent)
    }

    @Test
    fun testSerializeAndDeserializeUnknownOpenBrokerEvent() {
        val contractSigned = ContractSigned(Reference("1", "org.example"))
        val originalEvent: CloudEvent<ContractSigned> = openBrokerEvent(event = contractSigned, source = "org.something")
        val serializedEvent: String = jsonString(originalEvent)
        val deserializedEvent: CloudEvent<out OpenBrokerEvent> = parseOpenBrokerEvent(serializedEvent)
        val castedEvent: CloudEvent<ContractSigned> = deserializedEvent as CloudEvent<ContractSigned>
        assertEquals(originalEvent, castedEvent)
    }

    @Test
    fun testDeserializeOfferRejectedWithoutReasonDetails() {
        val event: CloudEvent<OfferRejected> = cloudEvent(TestObjectsJson.rejectOffer)
        assertEquals(OfferRejectedReason.NEW_APPLICATION_SUBMITTED, event.data!!.reason)
        assertNull(event.data!!.reasonDetails)
    }

    @Test
    fun testDeserializeOfferRejectedWithReasonDetails() {
        val event: CloudEvent<OfferRejected> = cloudEvent(TestObjectsJson.rejectOfferWithReasonDetails)
        assertEquals(OfferRejectedReason.CANCELLED_BY_BANK, event.data!!.reason)
        assertEquals("Occupation", event.data!!.reasonDetails)
    }

    @Test
    fun testDeserializeOfferRejectedWithoutReasonFails() {
        val json = """
            {
                "cloudEventsVersion" : "0.1",
                "eventType" : "org.open-broker.v0.no.PrivateUnsecuredLoanOfferRejected",
                "eventTypeVersion" : "v0",
                "source" : "/mycontext",
                "eventID" : "C234-1234-1236",
                "eventTime" : "2018-04-05T17:31:00Z",
                "contentType" : "application/json",
                "data": { "brokerReference": { "id": "12345", "issuer": "io.klira" } }
            }
        """.trimIndent()
        assertThrows(Exception::class.java) { cloudEvent<OfferRejected>(json) }
    }

    @Test
    fun testSerializeAndDeserializeOfferRejectedWithReasonDetails() {
        val offerRejected = OfferRejected(
            brokerReference = Reference("1", "org.example"),
            reason = OfferRejectedReason.CANCELLED_BY_BANK,
            reasonDetails = "Occupation"
        )
        val originalEvent: CloudEvent<OfferRejected> = openBrokerEvent(event = offerRejected, source = "org.something")
        val serializedEvent: String = jsonString(originalEvent)
        assertTrue(serializedEvent.contains("\"reason\":\"CANCELLED_BY_BANK\""))
        assertTrue(serializedEvent.contains("\"reasonDetails\":\"Occupation\""))
        val deserializedEvent: CloudEvent<OfferRejected> = cloudEvent(serializedEvent)
        assertEquals(originalEvent, deserializedEvent)
    }
}