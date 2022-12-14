package org.openbroker.no.mortgage.model

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
        ssn.requireMatchRegex(ssnRegex, "ssn")

        val phoneRegex = Regex("^\\+[1-9][0-9]{1,14}\$")
        phone?.requireMatchRegex(phoneRegex, "phone")
        secondaryPhone.requireAllMatchRegex(phoneRegex, "secondaryPhone")
        employerPhone?.requireMatchRegex(phoneRegex, "employerPhone")
        employmentStatusSinceYear.requireInRange(1900, 3000, "employmentStatusSinceYear")
        employmentStatusSinceMonth.requireInRange(1, 12, "employmentStatusSinceMonth")
        dependentChildren.requireInRange(0, 15, "dependentChildren")
        childSupportReceivedMonthly.requireMin(0, "childSupportReceivedMonthly")
        rentReceivedMonthly.requireMin(0, "rentReceivedMonthly")
        otherIncomeReceivedMonthly.requireMin(0, "otherIncomeReceivedMonthly")
        housingCostPerMonth.requireMin(0, "housingCostPerMonth")
        monthlyNetIncome.requireMin(0, "monthlyNetIncome")
        monthlyGrossIncome.requireMin(0, "monthlyGrossIncome")
        monthlyNetIncome.requireLessThanOrEqual(monthlyGrossIncome, "monthlyGrossIncome", "monthlyNetIncome")
    }
}