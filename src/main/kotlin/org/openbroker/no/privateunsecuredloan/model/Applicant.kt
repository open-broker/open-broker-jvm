package org.openbroker.no.privateunsecuredloan.model

import org.openbroker.common.*
import org.openbroker.common.requireInRange
import org.openbroker.common.requireMatchRegex
import org.openbroker.common.requireMin
import org.openbroker.no.model.Address
import org.openbroker.no.model.EmploymentStatus
import org.openbroker.no.model.HousingType
import org.openbroker.no.model.MaritalStatus

data class Applicant @JvmOverloads constructor(
    val ssn: String,
    val customerId: String,
    val phone: String? = null,
    val secondaryPhone: List<String> = emptyList(),
    val emailAddress: String? = null,
    val employmentStatus: EmploymentStatus,
    val employmentStatusSinceYear: Int,
    val employmentStatusSinceMonth: Int,
    val employmentStatusUntilYear: Int? = null,
    val employmentStatusUntilMonth: Int? = null,
    val employerName: String? = null,
    val employerPhone: String? = null,
    val dependentChildren: Int,
    val childSupportReceivedMonthly: Int? = null,
    val rentReceivedMonthly: Int? = null,
    val otherIncomeReceivedMonthly: Int? = null,
    val childSupportPaidMonthly: Int? = null,
    val paymentRemark: Boolean,
    val housingType: HousingType,
    val housingSinceYear: Int? = null,
    val housingSinceMonth: Int? = null,
    val housingCostPerMonth: Int,
    val netMonthlyIncome: Int? = null,
    val grossYearlyIncome: Int,
    val partnerGrossYearlyIncome: Int? = null,
    val maritalStatus: MaritalStatus,
    val bankAccount: String? = null,
    val citizenships: List<String>,
    val livedInCountrySinceYear: Int? = null,
    val countriesOfResidence: List<String> = emptyList(),
    val taxResidentOf: List<String> = emptyList(),
    val education: Education? = null,
    val tentativeAddress: Address? = null
) {
    init {
        val ssnRegex = Regex("^[0-9]{11}$")
        ssn.requireMatchRegex(ssnRegex, "ssn")

        val phoneRegex = Regex("^\\+[1-9][0-9]{1,14}\$")
        phone?.requireMatchRegex(phoneRegex, "phone")
        secondaryPhone.requireAllMatchRegex(phoneRegex, "secondaryPhone")

        employerPhone?.requireMatchRegex(phoneRegex, "employerPhone")
        employmentStatusSinceYear.requireInRange(1900, 3000, "employmentStatusSinceYear")
        employmentStatusSinceMonth.requireInRange(1, 12, "employmentStatusSinceMonth")
        employmentStatusUntilYear?.requireInRange(1900, 3000, "employmentStatusUntilYear")
        employmentStatusUntilMonth?.requireInRange(1, 12, "employmentStatusUntilMonth")
        housingSinceYear?.requireInRange(1900, 3000, "housingSinceYear")
        housingSinceMonth?.requireInRange(1, 12, "housingSinceMonth")
        dependentChildren.requireInRange(0, 15, "dependentChildren")
        livedInCountrySinceYear?.requireInRange(1900, 3000, "livedInCountrySinceYear")
        childSupportReceivedMonthly.requireMin(0, "childSupportReceivedMonthly")
        rentReceivedMonthly.requireMin(0, "rentReceivedMonthly")
        otherIncomeReceivedMonthly.requireMin(0, "otherIncomeReceivedMonthly")
        childSupportPaidMonthly.requireMin(0, "childSupportPaidMonthly")
        housingCostPerMonth.requireMin(0, "housingCostPerMonth")
        netMonthlyIncome?.requireMin(0, "netMonthlyIncome")
        grossYearlyIncome.requireMin(0, "grossYearlyIncome")

        val bankAccountRegex = Regex("^\\d{11}$")
        bankAccount?.requireMatchRegex(bankAccountRegex, "bankAccount")
        citizenships.requireNotEmpty("citizenships")
        val countryCodeRegex = Regex("^[A-Z]{2}$|OTHER")
        countriesOfResidence.requireAllMatchRegex(countryCodeRegex, "countriesOfResidence")
        taxResidentOf.requireAllMatchRegex(countryCodeRegex, "taxResidentOf")
        customerId.requireNotEmpty("customerId")
    }

}