package org.openbroker.se.mortgage

import org.openbroker.cloudevents.CloudEvent
import org.openbroker.common.model.Address
import org.openbroker.se.model.BankAccount
import org.openbroker.common.model.DataProtectionContext
import org.openbroker.se.model.EmploymentStatus
import org.openbroker.se.model.HousingType
import org.openbroker.se.model.MaritalStatus
import org.openbroker.common.model.Reference
import org.openbroker.common.openBrokerEvent
import org.openbroker.se.mortgage.events.ApplicationCreated
import org.openbroker.se.mortgage.events.Disbursed
import org.openbroker.se.mortgage.events.OfferAccepted
import org.openbroker.se.mortgage.events.OfferRejected
import org.openbroker.se.mortgage.events.Offering
import org.openbroker.se.mortgage.events.ApplicationRejection
import org.openbroker.se.mortgage.events.ContractSent
import org.openbroker.se.mortgage.events.ContractSigned
import org.openbroker.se.mortgage.events.Message
import org.openbroker.se.mortgage.model.Applicant
import org.openbroker.se.mortgage.model.Application
import org.openbroker.se.mortgage.model.ExistingLoan
import org.openbroker.se.mortgage.model.ExistingLoanType
import org.openbroker.se.mortgage.model.Offer
import org.openbroker.se.mortgage.model.OwnedProperty
import org.openbroker.se.mortgage.model.PropertyAddress
import org.openbroker.se.mortgage.model.PropertyType
import org.openbroker.se.mortgage.model.RefinancingProperty

object TestObjects {

    object Scenario1 {

        private val reference = Reference("S1", "io.klira")

        val message: CloudEvent<Message> = openBrokerEvent(
            event = Message(reference, "Hello there!", false),
            source = "http://snabbacash.se"
        )

        val applicationCreated1: CloudEvent<ApplicationCreated> = openBrokerEvent(
            ApplicationCreated(
                brokerReference = reference,
                application = Application(
                    applicant = Applicant(
                        ssn = "198206172215",
                        phone = null,
                        secondaryPhone = emptyList(),
                        emailAddress = "anton@zensum.se",
                        paymentRemark = false,
                        tentativeAddress = Address("Anton", "Österberg", "Lilla gatan", "Sthlm", "12632"),
                        swedishCitizen = true,
                        maritalStatus = MaritalStatus.COHABITING,
                        monthlyIncome = 12000,
                        employerPhone = null,
                        employerName = "Zensum",
                        housingType = HousingType.LIVE_IN,
                        dependentChildren = 14,
                        employmentStatusSinceMonth = 11,
                        employmentStatusSinceYear = 2012,
                        employmentStatus = EmploymentStatus.FULL_TIME,
                        childBenefitReceived = 200,
                        childSupportPaid = 411,
                        childSupportReceived = 390,
                        dividendReceived = 801,
                        housingBenefitReceived = 222,
                        monthlyRent = 6500,
                        otherReceived = 100,
                        pensionReceived = 0,
                        savingsCash = 500_000,
                        savingsMutualFunds = 80_000,
                        savingsOther = 89_000,
                        savingsStocks = 25_000,
                        studentBenefitReceived = 900,
                        welfareReceived = 700
                    ),
                    existingLoans = listOf(
                        ExistingLoan(43_000, 980, ExistingLoanType.CREDIT_CARD),
                        ExistingLoan(89_000, 221, ExistingLoanType.OTHER)
                    ),
                    coApplicant = null,
                    adultsInHousehold = 2,
                    ownedProperties = listOf(
                        OwnedProperty(PropertyType.TERRACED_HOUSE, 160_000, 3900, 50)
                    ),
                    refinancingProperty = RefinancingProperty(
                        propertyAddress = PropertyAddress("Eriksdalsgatan 142", "Stockholm", "11859", "1102"),
                        assessedValue = 1_370_000,
                        balcony = false,
                        elevator = true,
                        existingMortgage = 800_000,
                        monthlyCost = 4500,
                        ownershipShare = 40,
                        propertyType = PropertyType.APARTMENT,
                        rooms = 2,
                        squareMeters = 70,
                        interestRate = "5"
                    ),
                    extensions = emptyMap()
                ),
                dataProtectionContext = DataProtectionContext.FICTIONAL
            ),
            source = "http://google.se"
        )

        val rejection: CloudEvent<ApplicationRejection> = openBrokerEvent(
            ApplicationRejection(reference),
            "se.snabbacash"
        )

        val offering1: CloudEvent<Offering> = openBrokerEvent(
            Offering(
                brokerReference = reference,
                offer = Offer(
                    effectiveInterestRate = "4.5",
                    nominalInterestRate = "4.1",
                    termYears = 35,
                    termFee = 20,
                    monthlyCost = 2312,
                    invoiceFee = 10,
                    arrangementFee = 150
                )
            ),
            source = "https://snabbacash.se"
        )

        val offerRejected: CloudEvent<OfferRejected> = openBrokerEvent(
            OfferRejected(brokerReference = reference),
            source = "io.klira"
        )

        val offerAccepted: CloudEvent<OfferAccepted> = openBrokerEvent(
            OfferAccepted(
                brokerReference = reference,
                bankAccount = BankAccount("3300", "5611270415")
            ),
            source = "http://snabbacash.se"
        )

        val contractSent: CloudEvent<ContractSent> = openBrokerEvent(
            event = ContractSent(
                brokerReference = reference
            ),
            source = "http://snabbacash.se"
        )

        val contractSigned: CloudEvent<ContractSigned> = openBrokerEvent(
            event = ContractSigned(
                brokerReference = reference
            ),
            source = "http://snabbacash.se"
        )

        val disbursed: CloudEvent<Disbursed> = openBrokerEvent(
            event = Disbursed(
                brokerReference = reference,
                amountDisbursed = 240_000,
                date = "2019-02-14"
            ),
            source = "se.snabbacash"
        )
    }


    object Scenario2 {
        private val reference = Reference("A1", "io.klira")
    }
}