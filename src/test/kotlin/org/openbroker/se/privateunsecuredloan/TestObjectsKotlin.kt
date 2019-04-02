package org.openbroker.se.privateunsecuredloan

import org.openbroker.cloudevents.CloudEvent
import org.openbroker.common.model.Address
import org.openbroker.se.model.BankAccount
import org.openbroker.common.model.DataProtectionContext
import org.openbroker.se.model.EmploymentStatus
import org.openbroker.se.model.HousingType
import org.openbroker.se.model.MaritalStatus
import org.openbroker.common.model.Reference
import org.openbroker.common.openBrokerEvent
import org.openbroker.se.privateunsecuredloan.events.ApplicationCreated
import org.openbroker.se.privateunsecuredloan.events.DelayedProcessing
import org.openbroker.se.privateunsecuredloan.events.Disbursed
import org.openbroker.se.privateunsecuredloan.events.OfferAccepted
import org.openbroker.se.privateunsecuredloan.events.OfferRejected
import org.openbroker.se.privateunsecuredloan.events.Offering
import org.openbroker.se.privateunsecuredloan.events.Rejection
import org.openbroker.se.privateunsecuredloan.events.StatusUpdated
import org.openbroker.se.privateunsecuredloan.model.AmortizationType
import org.openbroker.se.privateunsecuredloan.model.Applicant
import org.openbroker.se.privateunsecuredloan.model.Application
import org.openbroker.se.privateunsecuredloan.model.DelayReason
import org.openbroker.se.privateunsecuredloan.model.LoanPurpose
import org.openbroker.se.privateunsecuredloan.model.Offer
import org.openbroker.se.privateunsecuredloan.model.Status

object TestObjectsKotlin {

    object Scenario1 {

        private val reference = Reference("S1", "io.klira")

        val applicationCreated: CloudEvent<ApplicationCreated> = openBrokerEvent(
            ApplicationCreated(
                Application(
                    applicant = Applicant(
                        ssn = "198206172215",
                        phone = null,
                        secondaryPhone = emptyList(),
                        emailAddress = "anton@zensum.se",
                        paymentRemark = false,
                        bankAccount = null,
                        tentativeAddress = Address("Anton", "Österberg", "Lilla gatan", "Sthlm", "12632"),
                        taxResidentOf = listOf("SE", "US"),
                        countriesOfResidence = listOf("SE"),
                        citizenships = listOf("SE", "FI"),
                        maritalStatus = MaritalStatus.COHABITING,
                        housingCostPerMonth = 5600,
                        monthlyIncome = 12000,
                        employerPhone = null,
                        employerName = "Zensum",
                        housingType = HousingType.LIVE_IN,
                        dependentChildren = 14,
                        employmentStatusSinceMonth = 11,
                        employmentStatusSinceYear = 2012,
                        employmentStatus = EmploymentStatus.FULL_TIME,
                        childSupportPaidMonthly = 2312,
                        childSupportReceivedMonthly = 23222
                    ),
                    termMonths = 22,
                    loanAmount = 250000,
                    existingLoans = emptyList(),
                    loanPurpose = LoanPurpose.DIVORCE_PROCEEDINGS,
                    coApplicant = null,
                    refinanceAmount = 0,
                    extensions = emptyMap()
                ),
                brokerReference = reference,
                dataProtectionContext = DataProtectionContext.FICTIONAL
            ),
            source = "http://google.se"
        )

        val delayedProcessing: CloudEvent<DelayedProcessing> = openBrokerEvent(
            DelayedProcessing(
                reference,
                DelayReason.BANK_HOLIDAY
            ),
            "http://google.se"
        )

        val rejection: CloudEvent<Rejection> = openBrokerEvent(
            Rejection(reference),
            "se.snabbacash"
        )

        val offering: CloudEvent<Offering> = openBrokerEvent(
            Offering(
                brokerReference = reference,
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
                )
            ),
            source = "https://thabank.com"
        )

        val offerRejected: CloudEvent<OfferRejected> = openBrokerEvent(
            OfferRejected(brokerReference = reference),
            source = "io.klira"
        )

        val offerAccepted: CloudEvent<OfferAccepted> = openBrokerEvent(
            OfferAccepted(brokerReference = reference),
            source = "http://google.se"
        )

        val contractSent: CloudEvent<StatusUpdated> = openBrokerEvent(
            event = StatusUpdated(
                brokerReference = reference,
                status = Status.CONTRACT_SENT_TO_CUSTOMER
            ),
            source = "se.snabbacash"
        )

        val contractSigned: CloudEvent<StatusUpdated> = openBrokerEvent(
            event = StatusUpdated(
                brokerReference = reference,
                status = Status.CONTRACT_SIGNED
            ),
            source = "se.snabbacash"
        )

        val disbursed: CloudEvent<Disbursed> = openBrokerEvent(
            event = Disbursed(
                brokerReference = reference,
                amountBrokered = 240_000,
                amountDisbursed = 240_000
            ),
            source = "se.snabbacash"
        )
    }


    object Scenario2 {
        private val reference = Reference("A1", "io.klira")

        val applicationCreated: CloudEvent<ApplicationCreated> = openBrokerEvent(
            event = ApplicationCreated(
                Application(
                    applicant = Applicant(
                        ssn = "198711016819",
                        phone = "+46735711157",
                        secondaryPhone = emptyList(),
                        emailAddress = "anton@zensum.se",
                        paymentRemark = false,
                        bankAccount = null,
                        tentativeAddress = Address("Erik", "Svensson", "Lilla gatan", "Hägersten", "12632"),
                        taxResidentOf = listOf("SE", "US"),
                        countriesOfResidence = listOf("SE"),
                        citizenships = listOf("SE"),
                        maritalStatus = MaritalStatus.COHABITING,
                        housingCostPerMonth = 5600,
                        monthlyIncome = 12000,
                        employerPhone = null,
                        employerName = "Zensum",
                        housingType = HousingType.RENTED,
                        dependentChildren = 3,
                        employmentStatusSinceMonth = 11,
                        employmentStatusSinceYear = 2012,
                        employmentStatus = EmploymentStatus.FULL_TIME,
                        childSupportPaidMonthly = 2312,
                        childSupportReceivedMonthly = 0
                    ),
                    termMonths = 22,
                    loanAmount = 250000,
                    existingLoans = emptyList(),
                    loanPurpose = LoanPurpose.DIVORCE_PROCEEDINGS,
                    coApplicant = null,
                    refinanceAmount = 0,
                    extensions = emptyMap()
                ),
                brokerReference = reference,
                dataProtectionContext = DataProtectionContext.FICTIONAL
            ),
            source = "sverker"
        )

        val offering: CloudEvent<Offering> = openBrokerEvent(
            event = Offering(
                brokerReference = reference,
                offer = Offer(
                    effectiveInterestRate = "12.13",
                    amortizationType = AmortizationType.ANNUITY,
                    arrangementFee = 0,
                    invoiceFee = 0,
                    maxOfferedCredit = 260_000,
                    offeredCredit = 250_000,
                    minOfferedCredit = 240_000,
                    mustRefinance = 220_000,
                    nominalInterestRate = "11.95",
                    termFee = 0,
                    termMonths = 48
                ),
                loanInsuranceOffer = null
            ),
            source = "se.snabbacash"
        )

        val accepted: CloudEvent<OfferAccepted> = openBrokerEvent(
            event = OfferAccepted(
                brokerReference = reference,
                bankAccount = BankAccount("5491", "0000003"),
                requestedCredit = 240_000
            ),
            source = "sverker"
        )

        val contractSent: CloudEvent<StatusUpdated> = openBrokerEvent(
            event = StatusUpdated(
                brokerReference = reference,
                status = Status.CONTRACT_SENT_TO_CUSTOMER
            ),
            source = "se.snabbacash"
        )

        val contractSigned: CloudEvent<StatusUpdated> = openBrokerEvent(
            event = StatusUpdated(
                brokerReference = reference,
                status = Status.CONTRACT_SIGNED
            ),
            source = "se.snabbacash"
        )

        val disbursed: CloudEvent<Disbursed> = openBrokerEvent(
            event = Disbursed(
                brokerReference = reference,
                amountBrokered = 240_000,
                amountDisbursed = 240_000
            ),
            source = "se.snabbacash"
        )
    }
}