package org.openbroker.no.privateunsecuredloan

object TestObjectsJson {
    internal val mainApplicant1 =
        """
        {
            "ssn": "31128012345",
            "phone": "+4740123456",
            "secondaryPhone": [],
            "emailAddress": "example@example.com",
            "employmentStatus": "PUBLIC_SECTOR",
            "employmentStatusSinceYear": 2010,
            "employmentStatusSinceMonth": 12,
            "employerName": "Company Inc.",
            "employerPhone": "+4740123456",
            "dependentChildren": 0,
            "childSupportReceivedMonthly": null,
            "rentReceivedMonthly": null,
            "otherIncomeReceivedMonthly": 4000,
            "childSupportPaidMonthly": null,
            "paymentRemark": false,
            "housingType": "LODGER",
            "housingSinceYear": 2010,
            "housingSinceMonth": 1,
            "housingCostPerMonth": 10000,
            "netMonthlyIncome": 30000,
            "grossYearlyIncome": 468000,
            "partnerYearlyIncome": 500000,
            "maritalStatus": "COHABITING",
            "bankAccount": {
                "accountNo": "12345678901"
            },
            "citizenships": [
                "NO"
            ],
            "livedInCountrySinceYear": 2010,
            "countriesOfResidence": [
                "NO"
            ],
            "taxResidentOf": [
                "NO"
            ],
            "education": "UNIVERSITY_SHORT",
            "tentativeAddress": {
                "firstName": "Jane",
                "lastName": "Doe",
                "address": "Exempelvägen 1",
                "postalCode": "12345",
                "city": "Exempelstad"
            }
        }
		""".trimIndent()

    internal val coApplicant1 =
        """
        {
            "ssn": "31127012345",
            "phone": "+4740123456",
            "secondaryPhone": [],
            "emailAddress": "example@example.com",
            "employmentStatus": "PRIVATE_SECTOR",
            "employmentStatusSinceYear": 2010,
            "employmentStatusSinceMonth": 12,
            "employerName": "Företag AB.",
            "employerPhone": "+4740123456",
            "dependentChildren": 0,
            "childSupportReceivedMonthly": null,
            "rentReceivedMonthly": null,
            "otherIncomeReceivedMonthly": 4000,
            "childSupportPaidMonthly": null,
            "paymentRemark": false,
            "housingType": "OWN_APARTMENT",
            "housingSinceYear": 2010,
            "housingSinceMonth": 1,
            "housingCostPerMonth": 1000,
            "netMonthlyIncome": 25000,
            "grossYearlyIncome": 390000,
            "partnerYearlyIncome": null,
            "maritalStatus": "SINGLE",
            "bankAccount": {
                "accountNo": "12345678901"
            },
            "citizenships": [
                "NO"
            ],
            "livedInCountrySinceYear": 2010,
            "countriesOfResidence": [
                "NO"
            ],
            "taxResidentOf": [
                "NO"
            ],
            "education": "UNIVERSITY_LONG",
            "tentativeAddress": {
                "firstName": "John",
                "lastName": "Doe",
                "address": "Exempelgatan 100",
                "postalCode": "12345",
                "city": "Exempelstad"
            }
        }
		""".trimIndent()

    @JvmField
    internal val fullApplicationCreatedJson =
        """
		{
    "cloudEventsVersion" : "0.1",
    "eventType" : "org.open-broker.v0.no.PrivateUnsecuredLoanApplicationCreated",
    "eventTypeVersion" : "v0",
    "source" : "/mycontext",
    "eventID" : "C234-1234-1234",
    "eventTime" : "2018-04-05T17:31:00Z",
    "extensions" : {
      "comExampleExtension" : "value"
    },
    "contentType" : "application/json",
    "data": {
		"application": {
			"applicant": $mainApplicant1,
			"coApplicant": null,
			"existingLoans": [
				{
					"loanAmount": 4000,
					"monthlyPayment": 22,
					"refinanceAmount": 3000,
					"existingLoanType": "CAR_LOAN",
					"responsibility": "SHARED",
                    "lender": "Example Bank"
				},
				{
					"loanAmount": 15000,
					"monthlyPayment": 56,
					"refinanceAmount": 15000,
					"existingLoanType": "STUDENT_LOAN",
					"responsibility": "MAIN_APPLICANT",
                    "lender": "Other"
				}
			],
			"extensions": {
			    "io.klira.someExtensionProperty": 42,
				"io.klira.someOtherExtensionProperty": 10000
			},
			"loanAmount": 20000,
			"loanPurpose": "REFINANCE",
			"refinanceAmount": 18000,
			"termMonths": 24
		},
		"brokerReference": {
			"issuer": "io.klira",
			"id": "1"
		},
		"dataProtectionContext": "FICTIONAL"
	}
}
	""".trimIndent()

    @JvmField
    internal val fullApplicationCreatedWithCoApplicantJson =
        """
		{
    "cloudEventsVersion" : "0.1",
    "eventType" : "org.open-broker.v0.no.PrivateUnsecuredLoanApplicationCreated",
    "eventTypeVersion" : "v0",
    "source" : "/mycontext",
    "eventID" : "C234-1234-1234",
    "eventTime" : "2018-04-05T17:31:00Z",
    "extensions" : {
      "comExampleExtension" : "value"
    },
    "contentType" : "application/json",
    "data": {
		"application": {
			"applicant": $mainApplicant1,
			"coApplicant": $coApplicant1,
			"existingLoans": [
				{
					"loanAmount": 4000,
					"monthlyPayment": 22,
					"refinanceAmount": 3000,
					"existingLoanType": "CAR_LOAN",
					"responsibility": "SHARED",
                    "lender": "Example Bank"
				},
				{
					"loanAmount": 15000,
					"monthlyPayment": 56,
					"refinanceAmount": 15000,
					"existingLoanType": "STUDENT_LOAN",
					"responsibility": "MAIN_APPLICANT",
                    "lender": "Other"
				}
			],
			"extensions": {
			    "io.klira.someExtensionProperty": 42,
				"io.klira.someOtherExtensionProperty": 10000
			},
			"loanAmount": 20000,
			"loanPurpose": "REFINANCE",
			"refinanceAmount": 18000,
			"termMonths": 24
		},
		"brokerReference": {
			"issuer": "io.klira",
			"id": "1"
		},
		"dataProtectionContext": "FICTIONAL"
	}
}
	""".trimIndent()

    @JvmField
    internal val invalidEventType =
        """
{
    "cloudEventsVersion" : "0.1",
    "eventType" : "org.open-broker.v0.no.PrivateUnsecuredLoanApplication",
    "eventTypeVersion" : "v0",
    "source" : "/mycontext",
    "eventID" : "C234-1234-1234",
    "eventTime" : "2018-04-05T17:31:00Z",
    "extensions" : {
      "comExampleExtension" : "value"
    },
    "contentType" : "application/json",
    "data": {
		"application": {
			"applicant": $mainApplicant1,
			"coApplicant": null,
            "existingLoans": [
				{
					"loanAmount": 4000,
					"monthlyPayment": 22,
					"refinanceAmount": 3000,
					"existingLoanType": "CAR_LOAN",
					"responsibility": "SHARED",
                    "lender": "Example Bank"
				},
				{
					"loanAmount": 15000,
					"monthlyPayment": 56,
					"refinanceAmount": 15000,
					"existingLoanType": "STUDENT_LOAN",
					"responsibility": "MAIN_APPLICANT",
                    "lender": "Other"
				}
			],
			"extensions": {
			    "io.klira.someExtensionProperty": 42,
				"io.klira.someOtherExtensionProperty": 10000
			},
			"loanAmount": 20000,
			"loanPurpose": "REFINANCE",
			"refinanceAmount": 18000,
			"termMonths": 24
		},
		"brokerReference": {
			"issuer": "io.klira",
			"id": "2"
		},
		"dataProtectionContext": "FICTIONAL"
	}
}
		""".trimIndent()

    @JvmField
    internal val acceptOffer =
        """
        {
		    "cloudEventsVersion" : "0.1",
            "eventType" : "org.open-broker.v0.no.PrivateUnsecuredLoanOfferAccepted",
            "eventTypeVersion" : "v0",
            "source" : "/mycontext",
            "eventID" : "C234-1234-1234",
            "eventTime" : "2018-04-05T17:31:00Z",
            "extensions" : {
              "comExampleExtension" : "value"
            },
            "contentType" : "application/json",
            "data": {
			    "brokerReference": {
				    "id": "12345",
					"issuer": "io.klira"
				},
				"bankAccount": {
					"accountNo": "12345678901"
				},
				"requestedCredit": 25000
			}
        }

		""".trimIndent()

    @JvmField
    internal val rejectOffer =
        """
        {
		    "cloudEventsVersion" : "0.1",
            "eventType" : "org.open-broker.v0.no.PrivateUnsecuredLoanOfferRejected",
            "eventTypeVersion" : "v0",
            "source" : "/mycontext",
            "eventID" : "C234-1234-1234",
            "eventTime" : "2018-04-05T17:31:00Z",
            "extensions" : {
              "comExampleExtension" : "value"
            },
            "contentType" : "application/json",
            "data": {
			    "brokerReference": {
				    "id": "12345",
					"issuer": "io.klira"
				}
			}
        }

		""".trimIndent()

    // Please note that interest rate and monthly cost are
    // not computed values but rather some random values
    internal val loanOffering1: String =
        """
        {
		    "cloudEventsVersion" : "0.1",
            "eventType" : "org.open-broker.v0.no.PrivateUnsecuredLoanOffering",
            "eventTypeVersion" : "v0",
            "source" : "/mycontext",
            "eventID" : "C234-1234-1234",
            "eventTime" : "2018-04-05T17:31:00Z",
            "extensions" : {
              "comExampleExtension" : "value"
            },
            "contentType" : "application/json",
            "data": {
                "brokerReference": {
                    "id": "9",
                    "issuer": "io.klira"
                },
                "offer": {
                    "effectiveInterestRate": "0.125",
                    "nominalInterestRate": "0.118",
                    "minOfferedCredit": 65000,
                    "offeredCredit": 67000,
                    "maxOfferedCredit": 70000,
                    "monthlyCost": 2324,
                    "mustRefinance": 55000,
                    "arrangementFee": 50,
                    "termFee": 12,
                    "invoiceFee": 19,
                    "termMonths": 36,
                    "amortizationType": "ANNUITY"
                },
                "loanInsuranceOffer": null
            }
        }
		""".trimIndent()

    internal val message: String =
        """
        {
		    "cloudEventsVersion" : "0.1",
            "eventType" : "org.open-broker.v0.no.PrivateUnsecuredLoanMessage",
            "eventTypeVersion" : "v0",
            "source" : "/mycontext",
            "eventID" : "C234-1234-1234",
            "eventTime" : "2018-04-05T17:31:00Z",
            "extensions" : {
              "comExampleExtension" : "value"
            },
            "contentType" : "application/json",
            "data": {
                "brokerReference": {
                    "id": "9",
                    "issuer": "io.klira"
                },
                "message": "Hello World",
                "requiresAction": false
            }
        }
		""".trimIndent()
}