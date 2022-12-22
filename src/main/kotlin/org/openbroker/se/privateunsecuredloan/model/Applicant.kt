package org.openbroker.se.privateunsecuredloan.model

import org.openbroker.common.*
import org.openbroker.common.requireAllMatchRegex
import org.openbroker.common.requireInRange
import org.openbroker.common.requireMatchRegex
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
        ssn.requireMatchRegex(ssnRegex, "ssn")

        val phoneRegex = Regex("^\\+[1-9][0-9]{1,14}\$")
        phone?.requireMatchRegex(phoneRegex, "phone")
        secondaryPhone.requireAllMatchRegex(phoneRegex, "secondaryPhone")
        employerPhone?.requireMatchRegex(phoneRegex, "employerPhone")

        employmentStatusSinceYear.requireInRange(1900, 3000, "employmentStatusSinceYear")
        employmentStatusSinceMonth.requireInRange(1, 12, "employmentStatusSinceMonth")
        employmentStatusUntilYear?.requireInRange(1900, 3000, "employmentStatusUntilYear")
        employmentStatusUntilMonth?.requireInRange(1, 12, "employmentStatusUntilMonth")
        dependentChildren.requireInRange(0, 15, "dependentChildren")
        housingCostPerMonth.requireMin(0, "housingCostPerMonth")
        monthlyIncome.requireMin(0, "monthlyIncome")
        childSupportReceivedMonthly.requireMin(0, "childSupportReceivedMonthly")
        childSupportPaidMonthly.requireMin(0, "childSupportPaidMonthly")
        citizenships.requireNotEmpty("citizenships")
        countriesOfResidence.requireNotEmpty("countriesOfResidence")
        taxResidentOf.requireNotEmpty("taxResidentOf")
        customerId.requireNotEmpty("customerId")
    }
}
