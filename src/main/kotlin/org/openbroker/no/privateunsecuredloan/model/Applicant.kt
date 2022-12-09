package org.openbroker.no.privateunsecuredloan.model

import org.openbroker.common.obfuscateDigits
import org.openbroker.common.requireInRange
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
    val housingSinceYear: Int,
    val housingSinceMonth: Int,
    val housingCostPerMonth: Int,
    val netMonthlyIncome: Int,
    val grossYearlyIncome: Int,
    val partnerGrossYearlyIncome: Int,
    val maritalStatus: MaritalStatus,
    val bankAccount: String? = null,
    val citizenships: List<String>,
    val livedInCountrySinceYear: Int,
    val countriesOfResidence: List<String>,
    val taxResidentOf: List<String>,
    val education: Education,
    val tentativeAddress: Address? = null
) {
    init {
        val ssnRegex = Regex("^[0-9]{11}$")
        require(ssn.matches(ssnRegex)) { "Invalid SSN: '${obfuscateDigits(ssn)}'" }

        val phoneRegex = Regex("^\\+[1-9][0-9]{1,14}\$")
        phone?.let {
            require(it.matches(phoneRegex)) {
                "Invalid primary phone number: '$it'"
            }
        }
        require(secondaryPhone.all { it.matches(phoneRegex) }) {
            "Invalid secondary phone number in $secondaryPhone"
        }
        employerPhone?.let {
            require(it.matches(phoneRegex)) {
                "Invalid employer phone number: '$it'"
            }
        }
        employmentStatusSinceYear.requireInRange(1900, 3000, "employmentStatusSinceYear")
        employmentStatusSinceMonth.requireInRange(1, 12, "employmentStatusSinceMonth")
        employmentStatusUntilYear?.let {
            it.requireInRange(1900, 3000, "employmentStatusUntilYear")
        }
        employmentStatusUntilMonth?.let {
            it.requireInRange(1, 12, "employmentStatusUntilMonth")
        }
        housingSinceYear.requireInRange(1900, 3000, "housingSinceYear")
        housingSinceMonth.requireInRange(1, 12, "housingSinceMonth")
        dependentChildren.requireInRange(0, 15, "dependentChildren")
        livedInCountrySinceYear.requireInRange(1900, 3000, "livedInCountrySinceYear")
        childSupportReceivedMonthly.requireMin(0, "childSupportReceivedMonthly")
        rentReceivedMonthly.requireMin(0, "rentReceivedMonthly")
        otherIncomeReceivedMonthly.requireMin(0, "otherIncomeReceivedMonthly")
        childSupportPaidMonthly.requireMin(0, "childSupportPaidMonthly")
        housingCostPerMonth.requireMin(0, "housingCostPerMonth")
        netMonthlyIncome.requireMin(0, "netMonthlyIncome")
        grossYearlyIncome.requireMin(0, "grossYearlyIncome")
        val bankAccountRegex = Regex("^\\d{11}$")
        bankAccount?.let {
            require(it.matches(bankAccountRegex)) {
                "Invalid bank account: '${obfuscateDigits(bankAccount)}'"
            }
        }
        require(citizenships.isNotEmpty()) { "citizenships cannot be empty" }
        require(countriesOfResidence.isNotEmpty()) { "countriesOfResidence cannot be empty" }
        require(taxResidentOf.isNotEmpty()) { "taxResidentOf cannot be empty" }
        require(customerId.isNotEmpty()) { "customerId cannot be empty" }
    }

}