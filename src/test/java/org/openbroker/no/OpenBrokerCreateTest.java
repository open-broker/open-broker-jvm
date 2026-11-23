package org.openbroker.no;

import org.junit.jupiter.api.Test;
import org.openbroker.cloudevents.CloudEvent;
import org.openbroker.common.OpenBrokerEventKt;
import org.openbroker.common.model.AmortizationType;
import org.openbroker.common.model.Reference;
import org.openbroker.no.privateunsecuredloan.events.ContractSigned;
import org.openbroker.no.privateunsecuredloan.events.Disbursed;
import org.openbroker.no.privateunsecuredloan.events.OfferAccepted;
import org.openbroker.no.privateunsecuredloan.model.CreditorProduct;
import org.openbroker.no.privateunsecuredloan.model.LoanPart;
import org.openbroker.no.privateunsecuredloan.model.LoanPartType;
import org.openbroker.no.privateunsecuredloan.model.Offer;
import org.openbroker.no.privateunsecuredloan.model.OfferStatus;
import org.openbroker.no.privateunsecuredloan.model.OfferType;

import java.util.Collections;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OpenBrokerCreateTest {

    @Test
    public void testCreateOpenBrokerEvent() {
        CloudEvent<OfferAccepted> accept = OpenBrokerEventKt.create(
            new OfferAccepted(
                new Reference("1", "io.klira"),
                new Reference("123", "com.creditor"),
                "12345678901",
                null,
                56
            ),
            OfferAccepted.class,
            "source"
        );

        assertEquals("12345678901", accept.getData().getBankAccount());
        assertEquals(56, Objects.requireNonNull(accept.getData().getTermMonths()).intValue());
    }

    // Integrations call this 17-argument constructor; it must keep compiling.
    @Test
    public void testCreateOfferWithConstructorBeforeOfferTypes() {
        Offer offer = new Offer("0.1", "0.2", 100, 100, 100, null, 1, 50, null, 4, 4, 4, 12,
            AmortizationType.ANNUITY, null, CreditorProduct.UNSECURED_LOAN, null);

        assertEquals(OfferType.STANDARD, offer.getType());
        assertEquals(OfferStatus.NEW, offer.getStatus());
        assertEquals(0, offer.getLoanParts().size());
    }

    @Test
    public void testOfferBuilderWithRequiredFieldsOnly() {
        Offer offer = Offer.builder()
            .effectiveInterestRate("0.0919")
            .nominalInterestRate("0.0899")
            .offeredCredit(150_000)
            .arrangementFee(0)
            .termFee(30)
            .invoiceFee(0)
            .build();

        assertEquals(new Offer("0.0919", "0.0899", 150_000, 0, 30, 0), offer);
        assertEquals(OfferType.STANDARD, offer.getType());
        assertEquals(OfferStatus.NEW, offer.getStatus());
        assertEquals(CreditorProduct.UNSECURED_LOAN, offer.getCreditorProduct());
        assertEquals(0, offer.getLoanParts().size());
        assertNull(offer.getBrokerOfferId());
    }

    @Test
    public void testOfferBuilderWithOptionalFieldsAndLoanPart() {
        LoanPart loanPart = LoanPart.builder()
            .type(LoanPartType.TOP_UP)
            .topUpAmount(30_000)
            .offeredCredit(30_000)
            .build();

        Offer offer = Offer.builder()
            .effectiveInterestRate("0.0919")
            .nominalInterestRate("0.0899")
            .offeredCredit(150_000)
            .arrangementFee(0)
            .termFee(30)
            .invoiceFee(0)
            .amortizationType(AmortizationType.ANNUITY)
            .type(OfferType.DUAL)
            .brokerOfferId("a1")
            .issuedAt("2026-11-05T08:40:45Z")
            .existingLoanAtCreditor(120_000)
            .loanParts(Collections.singletonList(loanPart))
            .build();

        assertEquals(AmortizationType.ANNUITY, offer.getAmortizationType());
        assertEquals(OfferType.DUAL, offer.getType());
        assertEquals("a1", offer.getBrokerOfferId());
        assertEquals(120_000, Objects.requireNonNull(offer.getExistingLoanAtCreditor()).intValue());
        assertEquals(LoanPartType.TOP_UP, offer.getLoanParts().get(0).getType());
        assertEquals(30_000, Objects.requireNonNull(offer.getLoanParts().get(0).getTopUpAmount()).intValue());
    }

    @Test
    public void testBuildersRequireMandatoryFields() {
        assertThrows(IllegalArgumentException.class, () ->
            Offer.builder().effectiveInterestRate("0.0919").nominalInterestRate("0.0899")
                .arrangementFee(0).termFee(30).invoiceFee(0).build());
        assertThrows(IllegalArgumentException.class, () ->
            Offer.builder().nominalInterestRate("0.0899")
                .offeredCredit(150_000).arrangementFee(0).termFee(30).invoiceFee(0).build());
        assertThrows(IllegalArgumentException.class, () ->
            Offer.builder().effectiveInterestRate("0.0919")
                .offeredCredit(150_000).arrangementFee(0).termFee(30).invoiceFee(0).build());
        assertThrows(IllegalArgumentException.class, () ->
            LoanPart.builder().topUpAmount(30_000).build());
    }

    @Test
    public void testBuildersRunValidation() {
        assertThrows(IllegalArgumentException.class, () ->
            Offer.builder().effectiveInterestRate("0.0919").nominalInterestRate("0.0899")
                .offeredCredit(150_000).arrangementFee(0).termFee(30).invoiceFee(0)
                .issuedAt("2026-11-05").build());
        assertThrows(IllegalArgumentException.class, () ->
            LoanPart.builder().type(LoanPartType.COMBO).offeredCredit(0).build());
    }

    // Integrations call these constructors without a loan part type; they must keep compiling.
    @Test
    public void testCreateContractSignedAndDisbursedWithoutLoanPartType() {
        Reference reference = new Reference("1", "io.klira");

        assertNull(new ContractSigned(reference).getType());
        assertNull(new Disbursed(reference, new Reference("123", "com.creditor"), 150_000, 150_000).getType());
    }

    @Test
    public void testCreateContractSignedAndDisbursedForLoanPart() {
        Reference reference = new Reference("1", "io.klira");

        assertEquals(LoanPartType.COMBO, new ContractSigned(reference, LoanPartType.COMBO).getType());
        assertEquals(LoanPartType.COMBO,
            new Disbursed(reference, new Reference("123", "com.creditor"), 110_000, 110_000, LoanPartType.COMBO).getType());
    }
}
