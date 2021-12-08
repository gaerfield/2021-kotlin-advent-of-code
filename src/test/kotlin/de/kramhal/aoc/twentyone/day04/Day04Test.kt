package de.kramhal.aoc.twentyone.day04

import de.kramhal.aoc.twentyone.resource
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class Day04Test {
    val testValues = resource("Day04test.txt")
    val dranwNumbers =
        listOf(7, 4, 9, 5, 11, 17, 23, 2, 0, 14, 21, 24, 10, 16, 13, 6, 15, 25, 12, 22, 18, 20, 8, 19, 3, 26, 1)
    val firstBoard = BingoBoard(listOf(
            22, 13, 17, 11, 0,
            8, 2, 23, 4, 24,
            21, 9, 14, 16, 7,
            6, 10, 3, 18, 5,
            1, 12, 20, 15, 19,
    ))

    @Test
    fun parseInput() {
        val game = parseGame(testValues)
        assertEquals(dranwNumbers, game.drawnNumbers)
        assertEquals(firstBoard, game.boards[0])
    }

    @Test
    fun markNumbers() {
        firstBoard.mark(22)
    }
    @Test
    fun hasWonByRow() {
        listOf(21,9,14,16,7).forEach { firstBoard.mark(it) }
        assertTrue { firstBoard.hasWon() }
    }
    @Test
    fun hasWonByColumn() {
        listOf(17,23,14,3,20).forEach { firstBoard.mark(it) }
        assertTrue { firstBoard.hasWon() }
    }

    @Test
    fun part1() {
        assertEquals(4512, part1(testValues))
    }

    @Test
    fun part2() {
        assertEquals(0, part2(emptyList()))
    }
}