package org.openbroker.no.privateunsecuredloan.model

import org.openbroker.common.model.AmortizationType
import org.openbroker.common.requireDateTime
import org.openbroker.common.requireInRange
import org.openbroker.common.requireLessThanOrEqual
import org.openbroker.common.requireMatchRegex
import org.openbroker.common.requireMin
import org.openbroker.common.requireNotEmpty

data class Offer @JvmOverloads constructor(
    val effectiveInterestRate: String,
    val nominalInterestRate: String,
    val minOfferedCredit: Int? = null,
    val offeredCredit: Int,
    val maxOfferedCredit: Int? = null,
    val minimumMonthlyCost: Int? = null,
    val monthlyCost: Int? = null,
    val mustRefinance: Int? = null,
    val totalRepaymentAmount: Int? = null,
    val arrangementFee: Int,
    val termFee: Int,
    val invoiceFee: Int,
    val termMonths: Int? = null,
    val amortizationType: AmortizationType? = null,
    val customerUrl: String? = null,
    val creditorProduct: CreditorProduct = CreditorProduct.UNSECURED_LOAN,
    val condition: OfferCondition? = null,
    val type: OfferType = OfferType.STANDARD,
    val brokerOfferId: String? = null,
    val status: OfferStatus = OfferStatus.NEW,
    val issuedAt: String? = null,
    val validUntil: String? = null,
    val existingLoanAtCreditor: Int? = null,
    val loanParts: List<LoanPart> = emptyList()
) {
    init {
        val interestRateRegex = Regex("^0\\.([1-9]\\d?|\\d[1-9])(\\d+)?\$")
        effectiveInterestRate.requireMatchRegex(interestRateRegex, "effectiveInterestRate")
        nominalInterestRate.requireMatchRegex(interestRateRegex, "nominalInterestRate")
        minOfferedCredit?.requireMin(1, "minOfferedCredit")
        offeredCredit.requireMin(1, "offeredCredit")
        maxOfferedCredit?.requireMin(1, "maxOfferedCredit")
        minimumMonthlyCost?.requireMin(1, "minimumMonthlyCost")
        if (minOfferedCredit != null && maxOfferedCredit != null) {
            minOfferedCredit.requireLessThanOrEqual(maxOfferedCredit, "maxOfferedCredit", "minOfferedCredit")
            offeredCredit.requireInRange(minOfferedCredit, maxOfferedCredit, "offeredCredit")
        }
        mustRefinance?.requireMin(0, "mustRefinance")
        totalRepaymentAmount?.requireMin(1, "totalRepaymentAmount")
        arrangementFee.requireMin(0, "arrangementFee")
        termFee.requireMin(0, "termFee")
        invoiceFee.requireMin(0, "invoiceFee")
        termMonths?.requireMin(1, "termMonths")
        brokerOfferId?.requireNotEmpty("brokerOfferId")
        issuedAt?.requireDateTime("issuedAt")
        validUntil?.requireDateTime("validUntil")
        existingLoanAtCreditor.requireMin(0, "existingLoanAtCreditor")
    }

    /**
     * For Java callers, so they set only the fields they need. [build] runs the
     * same validation as the constructor.
     */
    class Builder {
        private var effectiveInterestRate: String? = null
        private var nominalInterestRate: String? = null
        private var minOfferedCredit: Int? = null
        private var offeredCredit: Int? = null
        private var maxOfferedCredit: Int? = null
        private var minimumMonthlyCost: Int? = null
        private var monthlyCost: Int? = null
        private var mustRefinance: Int? = null
        private var totalRepaymentAmount: Int? = null
        private var arrangementFee: Int? = null
        private var termFee: Int? = null
        private var invoiceFee: Int? = null
        private var termMonths: Int? = null
        private var amortizationType: AmortizationType? = null
        private var customerUrl: String? = null
        private var creditorProduct: CreditorProduct = CreditorProduct.UNSECURED_LOAN
        private var condition: OfferCondition? = null
        private var type: OfferType = OfferType.STANDARD
        private var brokerOfferId: String? = null
        private var status: OfferStatus = OfferStatus.NEW
        private var issuedAt: String? = null
        private var validUntil: String? = null
        private var existingLoanAtCreditor: Int? = null
        private var loanParts: List<LoanPart> = emptyList()

        fun effectiveInterestRate(value: String): Builder = apply { effectiveInterestRate = value }
        fun nominalInterestRate(value: String): Builder = apply { nominalInterestRate = value }
        fun minOfferedCredit(value: Int?): Builder = apply { minOfferedCredit = value }
        fun offeredCredit(value: Int): Builder = apply { offeredCredit = value }
        fun maxOfferedCredit(value: Int?): Builder = apply { maxOfferedCredit = value }
        fun minimumMonthlyCost(value: Int?): Builder = apply { minimumMonthlyCost = value }
        fun monthlyCost(value: Int?): Builder = apply { monthlyCost = value }
        fun mustRefinance(value: Int?): Builder = apply { mustRefinance = value }
        fun totalRepaymentAmount(value: Int?): Builder = apply { totalRepaymentAmount = value }
        fun arrangementFee(value: Int): Builder = apply { arrangementFee = value }
        fun termFee(value: Int): Builder = apply { termFee = value }
        fun invoiceFee(value: Int): Builder = apply { invoiceFee = value }
        fun termMonths(value: Int?): Builder = apply { termMonths = value }
        fun amortizationType(value: AmortizationType?): Builder = apply { amortizationType = value }
        fun customerUrl(value: String?): Builder = apply { customerUrl = value }
        fun creditorProduct(value: CreditorProduct): Builder = apply { creditorProduct = value }
        fun condition(value: OfferCondition?): Builder = apply { condition = value }
        fun type(value: OfferType): Builder = apply { type = value }
        fun brokerOfferId(value: String?): Builder = apply { brokerOfferId = value }
        fun status(value: OfferStatus): Builder = apply { status = value }
        fun issuedAt(value: String?): Builder = apply { issuedAt = value }
        fun validUntil(value: String?): Builder = apply { validUntil = value }
        fun existingLoanAtCreditor(value: Int?): Builder = apply { existingLoanAtCreditor = value }
        fun loanParts(value: List<LoanPart>): Builder = apply { loanParts = value }

        fun build(): Offer = Offer(
            effectiveInterestRate = requireNotNull(effectiveInterestRate) { "effectiveInterestRate is required" },
            nominalInterestRate = requireNotNull(nominalInterestRate) { "nominalInterestRate is required" },
            minOfferedCredit = minOfferedCredit,
            offeredCredit = requireNotNull(offeredCredit) { "offeredCredit is required" },
            maxOfferedCredit = maxOfferedCredit,
            minimumMonthlyCost = minimumMonthlyCost,
            monthlyCost = monthlyCost,
            mustRefinance = mustRefinance,
            totalRepaymentAmount = totalRepaymentAmount,
            arrangementFee = requireNotNull(arrangementFee) { "arrangementFee is required" },
            termFee = requireNotNull(termFee) { "termFee is required" },
            invoiceFee = requireNotNull(invoiceFee) { "invoiceFee is required" },
            termMonths = termMonths,
            amortizationType = amortizationType,
            customerUrl = customerUrl,
            creditorProduct = creditorProduct,
            condition = condition,
            type = type,
            brokerOfferId = brokerOfferId,
            status = status,
            issuedAt = issuedAt,
            validUntil = validUntil,
            existingLoanAtCreditor = existingLoanAtCreditor,
            loanParts = loanParts
        )
    }

    companion object {
        @JvmStatic
        fun builder(): Builder = Builder()
    }
}