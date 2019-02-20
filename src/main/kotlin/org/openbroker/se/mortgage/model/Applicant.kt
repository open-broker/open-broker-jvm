package org.openbroker.se.mortgage.model

import org.openbroker.common.model.Address
import org.openbroker.se.model.EmploymentStatus
import org.openbroker.se.model.HousingType
import org.openbroker.se.model.MaritalStatus
import org.openbroker.common.requireMin

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
    val monthlyIncome: Int,
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
    val paymentRemarks: Int,
    val swedishCitizen: Boolean,
    val tentativeAddress: Address? = null
) {

    init {
        require(ssn.matches(ssnRegex)) { "Invalid SSN" }

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

        emailAddress?.let {
            require(it.matches(emailRegex)) {
                "Invalid email: '$emailAddress'"
            }
        }

        require(employmentStatusSinceYear in 1900..3000)
        require(employmentStatusSinceMonth in 1..12)
        require(dependentChildren in 0..15)
        monthlyRent.requireMin(0, "monthlyRent")
        monthlyIncome.requireMin(0, "monthlyIncome")

        sequenceOf(
            childSupportPaid,
            childSupportReceived,
            childBenefitReceived,
            studentBenefitReceived,
            housingBenefitReceived,
            welfareReceived,
            pensionReceived,
            dividendReceived,
            otherReceived,
            savingsCash,
            savingsMutualFunds,
            savingsStocks,
            savingsOther,
            paymentRemarks
        ).forEach { it.requireMin(0) }
    }

    companion object {
        val ssnRegex = Regex("^[0-9]{12}$")
        val phoneRegex = Regex("^\\+[1-9][0-9]{1,14}\$")
        val emailRegex = Regex("(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])")
    }
}
