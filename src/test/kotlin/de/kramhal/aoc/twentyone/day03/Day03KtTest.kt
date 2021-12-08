package de.kramhal.aoc.twentyone.day03

import kotlin.test.Test
import kotlin.test.assertEquals

internal class Day03KtTest {

    val testData = listOf(
        "00100",
        "11110",
        "10110",
        "10111",
        "10101",
        "01111",
        "00111",
        "11100",
        "10000",
        "11001",
        "00010",
        "01010",
    )

    @Test
    fun testRecord() {
        val sut = DiagnosticsRecorder(listOf(
            "00100",
            "11111",
            "11011",
        ))
        // 11111 = 31
        assertEquals(31, sut.gamma())
    }

    @Test
    fun part1() { assertEquals(198, part1(testData)) }

    @Test
    fun testOxygenRating() {
        val sut = DiagnosticsRecorder(testData)
        // 11111 = 31
        assertEquals(23, sut.oxygenGeneratorRating())
    }

    @Test
    fun testCo2ScrubberRating() {
        val sut = DiagnosticsRecorder(testData)
        // 11111 = 31
        assertEquals(10, sut.co2ScrubberRating())
    }

    /**
     * After following these new instructions, you would have a horizontal position of 15 and a depth of 60.
     * (Multiplying these produces 900.)
     */
    @Test
    fun part2() { assertEquals(230, part2(testData)) }
}