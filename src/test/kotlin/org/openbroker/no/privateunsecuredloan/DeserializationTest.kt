package org.openbroker.no.privateunsecuredloan

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.openbroker.no.privateunsecuredloan.events.OfferAccepted
import org.openbroker.no.privateunsecuredloan.events.Offering
import org.openbroker.no.privateunsecuredloan.model.LoanPartType
import org.openbroker.no.privateunsecuredloan.model.OfferStatus
import org.openbroker.no.privateunsecuredloan.model.OfferType
import org.openbroker.parseJsonFromFile

/**
 * Parses the example files from the specification, so this library accepts
 * the same events as the schema.
 */
class DeserializationTest {

    private fun offering(file: String): Offering =
        parseJsonFromFile<Offering>("no/privateunsecuredloan/$file").data!!

    @Test
    fun parseOffering() {
        val offer = offering("PrivateUnsecuredLoanOffering").offer
        assertEquals(OfferType.STANDARD, offer.type)
        assertEquals(OfferStatus.NEW, offer.status)
        assertEquals("9a4e1c7b-3d2f-4b8a-8e5c-6f1d0a2b7c93", offer.brokerOfferId)
        assertEquals("2020-05-18T13:43:24Z", offer.issuedAt)
        assertEquals("2020-06-18T21:59:59Z", offer.validUntil)
        assertNull(offer.existingLoanAtCreditor)
        assertTrue(offer.loanParts.isEmpty())
    }

    @Test
    fun parseDualOffering() {
        val offering = offering("PrivateUnsecuredLoanOffering_Dual")
        assertEquals(OfferType.STANDARD, offering.offer.type)
        assertEquals(1, offering.supplementaryOffers.size)
        val dual = offering.supplementaryOffers[0]
        assertEquals(OfferType.DUAL, dual.type)
        assertEquals("9bfd3e05-d804-4c13-99c5-34924f044576", dual.brokerOfferId)
        assertEquals(216_233, dual.offeredCredit)
        assertTrue(dual.loanParts.isEmpty())
    }

    @Test
    fun parseMultipleOffering() {
        val offering = offering("PrivateUnsecuredLoanOffering_Multiple")
        assertEquals(4, offering.supplementaryOffers.size)
        assertTrue(offering.supplementaryOffers.all { it.type == OfferType.MULTIPLE })
        assertEquals(listOf(36, 96, 144, 180), offering.supplementaryOffers.map { it.termMonths })
    }

    @Test
    fun parseTopUpOffering() {
        val offer = offering("PrivateUnsecuredLoanOffering_TopUp").offer
        assertEquals(545_964, offer.existingLoanAtCreditor)
        val part = offer.loanParts.single()
        assertEquals(LoanPartType.TOP_UP, part.type)
        assertNull(part.creditorOfferId)
        assertEquals(34_921, part.topUpAmount)
        assertNull(part.termMonths)
    }

    @Test
    fun parseComboOffering() {
        val part = offering("PrivateUnsecuredLoanOffering_Combo").offer.loanParts.single()
        assertEquals(LoanPartType.COMBO, part.type)
        assertEquals("2000002", part.creditorOfferId)
        assertEquals(110_000, part.offeredCredit)
        assertNull(part.topUpAmount)
        assertEquals("0.13295", part.effectiveInterestRate)
    }

    @Test
    fun parseComboTopUpOffering() {
        val part = offering("PrivateUnsecuredLoanOffering_ComboTopUp").offer.loanParts.single()
        assertEquals(LoanPartType.COMBO_TOP_UP, part.type)
        assertEquals(254_426, part.offeredCredit)
        assertEquals(223_455, part.topUpAmount)
        assertEquals(0, part.mustRefinance)
    }

    @Test
    fun parseOfferAccepted() {
        val accepted = parseJsonFromFile<OfferAccepted>("no/privateunsecuredloan/PrivateUnsecuredLoanOfferAccepted").data!!
        assertEquals("9a4e1c7b-3d2f-4b8a-8e5c-6f1d0a2b7c93", accepted.brokerOfferId)
        assertEquals("12345678901", accepted.bankAccount)
    }
}
