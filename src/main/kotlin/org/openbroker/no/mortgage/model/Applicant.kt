package org.openbroker.no.mortgage.model

import org.openbroker.common.obfuscateDigits
import org.openbroker.common.requireMin
import org.openbroker.no.model.Address
import org.openbroker.no.model.EmploymentStatus
import org.openbroker.no.model.HousingType
import org.openbroker.no.model.MaritalStatus

data class Applicant @JvmOverloads constructor(
    val ssn: String,
    val phone: String? = null,
    val secondaryPhone: List<String> = emptyList(),
    val emailAddress: String? = null,
    val employmentStatus: EmploymentStatus,
    val employmentStatusSinceYear: Int,
    val employmentStatusSinceMonth: Int,
    val employerName: String? = null,
    val employerPhone: String? = null,
    val dependentChildren: Int,
    val childSupportReceivedMonthly: Int? = null,
    val rentReceivedMonthly: Int? = null,
    val otherIncomeReceivedMonthly: Int? = null,
    val childSupportPaidMonthly: Int? = null,
    val paymentRemark: Boolean,
    val housingType: HousingType,
    val housingCostPerMonth: Int,
    val monthlyGrossIncome: Int,
    val monthlyNetIncome: Int,
    val maritalStatus: MaritalStatus,
    val norwegianCitizen: Boolean,
    val education: Education? = null,
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
        require(employmentStatusSinceYear in 1900..3000)
        require(employmentStatusSinceMonth in 1..12)
        require(dependentChildren in 0..15)
        childSupportReceivedMonthly.requireMin(0, "childSupportReceivedMonthly")
        rentReceivedMonthly.requireMin(0, "rentReceivedMonthly")
        otherIncomeReceivedMonthly.requireMin(0, "otherIncomeReceivedMonthly")
        childSupportPaidMonthly.requireMin(0, "childSupportPaidMonthly")
        housingCostPerMonth.requireMin(0, "housingCostPerMonth")
        monthlyNetIncome.requireMin(0, "monthlyNetIncome")
        monthlyGrossIncome.requireMin(0, "monthlyGrossIncome")
    }

}