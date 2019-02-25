package org.openbroker.no.privateunsecuredloan

import org.openbroker.cloudevents.CloudEvent
import org.openbroker.common.model.Address
import org.openbroker.common.model.DataProtectionContext
import org.openbroker.common.model.Reference
import org.openbroker.common.openBrokerEvent
import org.openbroker.no.model.EmploymentStatus
import org.openbroker.no.model.HousingType
import org.openbroker.no.model.MaritalStatus
import org.openbroker.no.privateunsecuredloan.events.*
import org.openbroker.no.privateunsecuredloan.model.*

object TestObjectsKotlin {

    object Scenario1 {

        private val reference = Reference("S1", "io.klira")

        val applicationCreated: CloudEvent<ApplicationCreated> = openBrokerEvent(
            event = ApplicationCreated(
                Application(
                    applicant = Applicant(
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
                        tentativeAddress = Address("Jane", "Doe", "Exempelstigen 4", "Gräsdalen", "12345")
                    ),
                    coApplicant = null,
                    termMonths = 24,
                    loanAmount = 250000,
                    existingLoans = emptyList(),
                    loanPurpose = LoanPurpose.TRAVEL,
                    refinanceAmount = 0,
                    extensions = emptyMap()

                ),
                brokerReference = reference,
                dataProtectionContext = DataProtectionContext.FICTIONAL
            ),
            source = "http://broker.org"
        )

        val message: CloudEvent<Message> = openBrokerEvent(
            event = Message(
                message = "Hello World",
                requiresAction = false,
                brokerReference = reference
            ),
            source = "https://bank.no"
        )

        val rejection: CloudEvent<Rejection> = openBrokerEvent(
            event = Rejection(
                brokerReference = reference
            ),
            source = "https://bank.no"
        )

        val offering: CloudEvent<Offering> = openBrokerEvent(
            event = Offering(
                offer = Offer(
                    effectiveInterestRate = "14.5",
                    nominalInterestRate = "12.1",
                    termMonths = 56,
                    termFee = 20,
                    amortizationType = AmortizationType.ANNUITY,
                    monthlyCost = 2312,
                    invoiceFee = 10,
                    mustRefinance = 2000,
                    arrangementFee = 150,
                    minOfferedCredit = 150_000,
                    maxOfferedCredit = 160_000,
                    offeredCredit = 150_000
                ),
                brokerReference = reference
            ),
            source = "https://bank.no"
        )

        val offerRejected: CloudEvent<OfferRejected> = openBrokerEvent(
            event = OfferRejected(
                brokerReference = reference
            ),
            source = "http://broker.org"
        )

        val offerAccepted: CloudEvent<OfferAccepted> = openBrokerEvent(
            event = OfferAccepted(
                brokerReference = reference
            ),
            source = "http://broker.org"
        )

        val contractSigned: CloudEvent<ContractSigned> = openBrokerEvent(
            event = ContractSigned(
                brokerReference = reference
            ),
            source = "https://bank.no"
        )

        val disbursed: CloudEvent<Disbursed> = openBrokerEvent(
            event = Disbursed(
                amountBrokered = 240_000,
                amountDisbursed = 240_000,
                brokerReference = reference
            ),
            source = "https://bank.no"
        )
    }

    object Scenario2 {

        private val reference = Reference("S2", "io.klira")

        val applicationCreated: CloudEvent<ApplicationCreated> = openBrokerEvent(
            event = ApplicationCreated(
                Application(
                    applicant = Applicant(
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
                        tentativeAddress = Address("Jane", "Doe", "Exempelstigen 4", "Gräsdalen", "12345")
                    ),
                    coApplicant = null,
                    termMonths = 24,
                    loanAmount = 250000,
                    existingLoans = emptyList(),
                    loanPurpose = LoanPurpose.TRAVEL,
                    refinanceAmount = 0,
                    extensions = emptyMap()

                ),
                brokerReference = reference,
                dataProtectionContext = DataProtectionContext.FICTIONAL
            ),
            source = "http://broker.org"
        )

        val message1: CloudEvent<Message> = openBrokerEvent(
            event = Message(
                message = "Manual processing",
                requiresAction = false,
                brokerReference = reference
            ),
            source = "https://bank.no"
        )

        val offering: CloudEvent<Offering> = openBrokerEvent(
            event = Offering(
                offer = Offer(
                    effectiveInterestRate = "14.5",
                    nominalInterestRate = "12.1",
                    termMonths = 56,
                    termFee = 20,
                    amortizationType = AmortizationType.ANNUITY,
                    monthlyCost = 2312,
                    invoiceFee = 10,
                    mustRefinance = 2000,
                    arrangementFee = 150,
                    minOfferedCredit = 150_000,
                    maxOfferedCredit = 160_000,
                    offeredCredit = 150_000
                ),
                brokerReference = reference
            ),
            source = "https://bank.no"
        )

        val offerAccepted: CloudEvent<OfferAccepted> = openBrokerEvent(
            event = OfferAccepted(
                brokerReference = reference
            ),
            source = "http://broker.org"
        )

        val message2: CloudEvent<Message> = openBrokerEvent(
            event = Message(
                message = "Contract sent to customer",
                requiresAction = false,
                brokerReference = reference
            ),
            source = "https://bank.no"
        )

        val contractSigned: CloudEvent<ContractSigned> = openBrokerEvent(
            event = ContractSigned(
                brokerReference = reference
            ),
            source = "https://bank.no"
        )

        val message3: CloudEvent<Message> = openBrokerEvent(
            event = Message(
                message = "Need further documentation",
                requiresAction = true,
                brokerReference = reference
            ),
            source = "https://bank.no"
        )

        val disbursed: CloudEvent<Disbursed> = openBrokerEvent(
            event = Disbursed(
                amountBrokered = 240_000,
                amountDisbursed = 240_000,
                brokerReference = reference
            ),
            source = "https://bank.no"
        )
    }
}