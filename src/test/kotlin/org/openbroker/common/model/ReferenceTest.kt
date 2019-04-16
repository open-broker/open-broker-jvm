package org.openbroker.common.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

class ReferenceTest {

    @Test
    fun invalidIssuerInReferenceBadCharcterInDomain() {
        assertThrows<IllegalArgumentException> {
            Reference("1", "io.kl'ira")
        }
    }

    @Test
    fun invalidIssuerInReferenceNoDot() {
        assertThrows<IllegalArgumentException> {
            Reference("1", "ioklira")
        }
    }

    @Test
    fun invalidIssuerInReferenceEndingWithDot() {
        assertThrows<IllegalArgumentException> {
            Reference("1", "io.klira.")
        }
    }

    @Test
    fun validReference() {
        val reference = Reference("1", "com.example")
        assertEquals("com.example", reference.issuer)
    }

    @Test
    fun validReferenceWithSubdomain() {
        val reference = Reference("1", "com.example.sub")
        assertEquals("com.example.sub", reference.issuer)
    }
}