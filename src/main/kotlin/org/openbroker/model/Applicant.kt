package org.openbroker.model

import org.openbroker.requireMin

class Applicant @JvmOverloads constructor(
    val ssn: String,
    val phone: String? = null,
    val secondaryPhone: List<String> = emptyList(),
    val emailAddress: String? = null,
    val employmentStatus: EmploymentStatus,
    val employmentStatusSinceYear: Int,
    val employmentStatusSinceMonth: Int,
    val dependentChildren: Int,
    val housingType: HousingType,
    val housingCostPerMonth: Int,
    val employerName: String? = null,
    val employerPhone: String? = null,
    val monthlyIncome: Int,
    val childSupportReceivedMonthly: Int? = null,
    val childSupportPaidMonthly: Int? = null,
    val maritalStatus: MaritalStatus,
    val paymentRemark: Boolean,
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
            require(it.matches(phoneRegex)) { "Invalid phone number" }
        }
        require(secondaryPhone.all { it.matches(phoneRegex) }) { "Invalid secondary phone number" }
        employerPhone?.let {
            require(it.matches(phoneRegex)) { "Invalid phone number" }
        }

        require(employmentStatusSinceYear in 1900..3000)
        require(employmentStatusSinceMonth in 1..12)
        require(dependentChildren in 0..15)
        housingCostPerMonth.requireMin(0, "housingCostPerMonth")
        monthlyIncome.requireMin(0, "monthlyIncome")
        childSupportReceivedMonthly.requireMin(0, "childSupportReceivedMonthly")
        childSupportPaidMonthly.requireMin(0, "childSupportPaidMonthly")

        require(citizenships.isNotEmpty())
        require(countriesOfResidence.isNotEmpty())
        require(taxResidentOf.isNotEmpty())
    }
}
