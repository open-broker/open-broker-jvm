package org.openbroker.se.mortgage.model

import org.openbroker.common.*
import org.openbroker.common.requireAllMatchRegex
import org.openbroker.common.requireInRange
import org.openbroker.common.requireMatchRegex
import org.openbroker.common.requireMin
import org.openbroker.se.model.EmploymentStatus
import org.openbroker.se.model.HousingType
import org.openbroker.se.model.MaritalStatus
import org.openbroker.se.model.Address

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
    val housingType: HousingType,
    val monthlyRent: Int,
    val monthlyGrossIncome: Int,
    val monthlyNetIncome: Int,
    val maritalStatus: MaritalStatus,
    val childSupportPaid: Int = 0,
    val childSupportReceived: Int = 0,
    val childBenefitReceived: Int = 0,
    val studentBenefitReceived: Int = 0,
    val housingBenefitReceived: Int = 0,
    val welfareReceived: Int = 0,
    val pensionReceived: Int = 0,
    val dividendReceived: Int = 0,
    val otherReceived: Int = 0,
    val savingsCash: Int = 0,
    val savingsMutualFunds: Int = 0,
    val savingsStocks: Int = 0,
    val savingsOther: Int = 0,
    val paymentRemark: Boolean,
    val swedishCitizen: Boolean,
    val tentativeAddress: Address? = null
) {

    init {
        ssn.requireMatchRegex(ssnRegex, "ssn")
        phone?.requireMatchRegex(phoneRegex, "phone")
        secondaryPhone.requireAllMatchRegex(phoneRegex, "secondaryPhone")
        employerPhone?.requireMatchRegex(phoneRegex, "employerPhone")
        emailAddress?.requireMatchRegex(emailRegex, "emailAddress")

        employmentStatusSinceYear.requireInRange(1900, 3000, "employmentStatusSinceYear")
        employmentStatusSinceMonth.requireInRange(1, 12, "employmentStatusSinceMonth")
        dependentChildren.requireInRange(0, 15, "dependentChildren")
        monthlyRent.requireMin(0, "monthlyRent")
        monthlyNetIncome.requireMin(0, "monthlyIncome")
        monthlyGrossIncome.requireMin(0, "monthlyIncome")
        monthlyNetIncome.requireLessThanOrEqual(monthlyGrossIncome, "monthlyGrossIncome", "monthlyNetIncome")
        childSupportPaid.requireMin(0, "childSupportPaid")
        childSupportReceived.requireMin(0, "childSupportReceived")
        childBenefitReceived.requireMin(0, "childBenefitReceived")
        studentBenefitReceived.requireMin(0, "studentBenefitReceived")
        housingBenefitReceived.requireMin(0, "housingBenefitReceived")
        welfareReceived.requireMin(0, "welfareReceived")
        pensionReceived.requireMin(0, "pensionReceived")
        dividendReceived.requireMin(0, "dividendReceived")
        otherReceived.requireMin(0, "otherReceived")
        savingsCash.requireMin(0, "savingsCash")
        savingsMutualFunds.requireMin(0, "savingsMutualFunds")
        savingsStocks.requireMin(0, "savingsStocks")
        savingsOther.requireMin(0, "savingsOther")
    }

    companion object {
        val ssnRegex = Regex("^[0-9]{12}$")
        val phoneRegex = Regex("^\\+[1-9][0-9]{1,14}\$")
        val emailRegex = Regex("(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])")
    }
}
