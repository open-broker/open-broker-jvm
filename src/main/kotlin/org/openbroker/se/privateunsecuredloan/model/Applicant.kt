package org.openbroker.se.privateunsecuredloan.model

import org.openbroker.common.requireInRange
import org.openbroker.common.requireMin
import org.openbroker.se.model.*

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
    val dependentChildren: Int,
    val housingType: HousingType,
    val housingCostPerMonth: Int,
    val employerName: String? = null,
    val employerPhone: String? = null,
    val monthlyIncome: Int,
    val childSupportReceivedMonthly: Int? = null,
    val childSupportPaidMonthly: Int? = null,
    val maritalStatus: MaritalStatus,
    val paymentRemark: Boolean? = null,
    val bankAccount: BankAccount? = null,
    val citizenships: List<String>,
    val countriesOfResidence: List<String>,
    val taxResidentOf: List<String>,
    val tentativeAddress: Address? = null
) {

    init {
        val ssnRegex = Regex("^[0-9]{12}$")
        require(ssn.matches(ssnRegex)) { "Invalid SSN" }

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
        dependentChildren.requireInRange(0, 15, "dependentChildren")
        housingCostPerMonth.requireMin(0, "housingCostPerMonth")
        monthlyIncome.requireMin(0, "monthlyIncome")
        childSupportReceivedMonthly.requireMin(0, "childSupportReceivedMonthly")
        childSupportPaidMonthly.requireMin(0, "childSupportPaidMonthly")

        require(citizenships.isNotEmpty()) { "citizenships cannot be empty" }
        require(countriesOfResidence.isNotEmpty()) { "countriesOfResidence cannot be empty" }
        require(taxResidentOf.isNotEmpty()) { "taxResidentOf cannot be empty" }
        require(customerId.isNotEmpty()) { "customerId cannot be empty" }
    }
}
