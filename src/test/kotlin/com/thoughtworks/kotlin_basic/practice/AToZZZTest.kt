package com.thoughtworks.kotlin_basic.practice

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class AToZZZTest {
    private val service = AToZZZ()

    @Test
    fun `should return array of letter given valid input`() {

        assertEquals("A,B,C", service.convert(1, 3).joinToString(","))
    }

    @Test
    fun `should return from AA given start from 27`() {
        assertEquals("AA,AB,AC", service.convert(27, 3).joinToString(","))
    }

    @Test
    fun `should return error message given invalid start position`() {
        assertThrows(IllegalArgumentException::class.java){service.convert(-1, 3)}
    }

    @Test
    fun `should return error message given invalid count`() {
        assertThrows(IllegalArgumentException::class.java){service.convert(1, -3)}
    }

    @Test
    fun `should return error message given start more than ZZZ`() {
        assertThrows(IllegalArgumentException::class.java){service.convert(26*26*26 + 26*26 + 26 + 1, 1)}
    }

    @Test
    fun `should return error message given end more than ZZZ`() {
        assertThrows(IllegalArgumentException::class.java){service.convert(26*26*26 + 26*26 + 26, 2)}
    }
}