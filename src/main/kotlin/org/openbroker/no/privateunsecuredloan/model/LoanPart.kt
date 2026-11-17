package org.openbroker.no.privateunsecuredloan.model

import org.openbroker.common.model.AmortizationType
import org.openbroker.common.requireMatchRegex
import org.openbroker.common.requireMin
import org.openbroker.common.requireNotEmpty

/**
 * One part of an offer. Only [type] is required. A field that is not sent, or
 * is null, means the value is unknown. Field meanings are the same as in
 * [Offer], but for this part only.
 */
data class LoanPart @JvmOverloads constructor(
    val type: LoanPartType,
    val creditorOfferId: String? = null,
    val offeredCredit: Int? = null,
    val topUpAmount: Int? = null,
    val effectiveInterestRate: String? = null,
    val nominalInterestRate: String? = null,
    val monthlyCost: Int? = null,
    val mustRefinance: Int? = null,
    val totalRepaymentAmount: Int? = null,
    val arrangementFee: Int? = null,
    val termFee: Int? = null,
    val invoiceFee: Int? = null,
    val termMonths: Int? = null,
    val amortizationType: AmortizationType? = null
) {
    init {
        val interestRateRegex = Regex("^[0-9]+(\\.[0-9]+)?\$")
        creditorOfferId?.requireNotEmpty("creditorOfferId")
        offeredCredit.requireMin(1, "offeredCredit")
        topUpAmount.requireMin(1, "topUpAmount")
        effectiveInterestRate?.requireMatchRegex(interestRateRegex, "effectiveInterestRate")
        nominalInterestRate?.requireMatchRegex(interestRateRegex, "nominalInterestRate")
        monthlyCost.requireMin(1, "monthlyCost")
        mustRefinance.requireMin(0, "mustRefinance")
        totalRepaymentAmount.requireMin(1, "totalRepaymentAmount")
        arrangementFee.requireMin(0, "arrangementFee")
        termFee.requireMin(0, "termFee")
        invoiceFee.requireMin(0, "invoiceFee")
        termMonths.requireMin(1, "termMonths")
    }

    /**
     * For Java callers, so they set only the fields they need. [build] runs the
     * same validation as the constructor.
     */
    class Builder {
        private var type: LoanPartType? = null
        private var creditorOfferId: String? = null
        private var offeredCredit: Int? = null
        private var topUpAmount: Int? = null
        private var effectiveInterestRate: String? = null
        private var nominalInterestRate: String? = null
        private var monthlyCost: Int? = null
        private var mustRefinance: Int? = null
        private var totalRepaymentAmount: Int? = null
        private var arrangementFee: Int? = null
        private var termFee: Int? = null
        private var invoiceFee: Int? = null
        private var termMonths: Int? = null
        private var amortizationType: AmortizationType? = null

        fun type(value: LoanPartType): Builder = apply { type = value }
        fun creditorOfferId(value: String?): Builder = apply { creditorOfferId = value }
        fun offeredCredit(value: Int?): Builder = apply { offeredCredit = value }
        fun topUpAmount(value: Int?): Builder = apply { topUpAmount = value }
        fun effectiveInterestRate(value: String?): Builder = apply { effectiveInterestRate = value }
        fun nominalInterestRate(value: String?): Builder = apply { nominalInterestRate = value }
        fun monthlyCost(value: Int?): Builder = apply { monthlyCost = value }
        fun mustRefinance(value: Int?): Builder = apply { mustRefinance = value }
        fun totalRepaymentAmount(value: Int?): Builder = apply { totalRepaymentAmount = value }
        fun arrangementFee(value: Int?): Builder = apply { arrangementFee = value }
        fun termFee(value: Int?): Builder = apply { termFee = value }
        fun invoiceFee(value: Int?): Builder = apply { invoiceFee = value }
        fun termMonths(value: Int?): Builder = apply { termMonths = value }
        fun amortizationType(value: AmortizationType?): Builder = apply { amortizationType = value }

        fun build(): LoanPart = LoanPart(
            type = requireNotNull(type) { "type is required" },
            creditorOfferId = creditorOfferId,
            offeredCredit = offeredCredit,
            topUpAmount = topUpAmount,
            effectiveInterestRate = effectiveInterestRate,
            nominalInterestRate = nominalInterestRate,
            monthlyCost = monthlyCost,
            mustRefinance = mustRefinance,
            totalRepaymentAmount = totalRepaymentAmount,
            arrangementFee = arrangementFee,
            termFee = termFee,
            invoiceFee = invoiceFee,
            termMonths = termMonths,
            amortizationType = amortizationType
        )
    }

    companion object {
        @JvmStatic
        fun builder(): Builder = Builder()
    }
}
