package de.kramhal.aoc.twentyone

import kotlin.test.Test
import kotlin.test.assertEquals

internal class Day02Test {

    val testData = listOf(
        "forward 5",
        "down 5",
        "forward 8",
        "up 3",
        "down 8",
        "forward 2",
    )

    @Test
    fun testParseCommand() { assertEquals(Day02.MovementCommand(Day02.Direction.forward, 10), Day02.parseCommand("forward 10")) }

    @Test
    fun trackMovements() {
        val sut = Day02.MovementTracker()
        sut.command(Day02.MovementCommand(Day02.Direction.up, 2))
        sut.command(Day02.MovementCommand(Day02.Direction.forward, 3))
        assertEquals(-2, sut.level)
        assertEquals(3, sut.position)
    }


    /**
     * After following these instructions, you would have a horizontal position of 15 and a depth of 10.
     * (Multiplying these together produces 150.)
     */
    @Test
    fun part1() { assertEquals(150, Day02.part1(testData)) }

    /**
     * After following these new instructions, you would have a horizontal position of 15 and a depth of 60.
     * (Multiplying these produces 900.)
     */
    @Test
    fun part2() { assertEquals(900, Day02.part2(testData)) }
}