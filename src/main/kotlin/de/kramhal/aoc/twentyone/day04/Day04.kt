package de.kramhal.aoc.twentyone.day04

import de.kramhal.aoc.twentyone.resource

const val BINGO_BOARR_DIMENSION = 5
data class BingoBoard(val board: List<Int>) {
    val marks = mutableListOf<Int>()
    fun mark(i: Int) {
        val index = board.indexOf(i)
        if(index >= 0) marks.add(index)
    }

    fun hasWon(): Boolean {
        return rowWasCompleted() || columnWasCompleted()
    }

    private fun rowWasCompleted() =
        marks.containsAll(listOf(0,1,2,3,4)) ||
        marks.containsAll(listOf(5,6,7,8,9)) ||
        marks.containsAll(listOf(10,11,12,13,14)) ||
        marks.containsAll(listOf(15,16,17,18,19)) ||
        marks.containsAll(listOf(20,21,22,23,24))

    private fun columnWasCompleted() =
        marks.containsAll(listOf(0,5,10,15,20)) ||
        marks.containsAll(listOf(1,6,11,16,21)) ||
        marks.containsAll(listOf(2,7,12,17,22)) ||
        marks.containsAll(listOf(3,8,13,18,23)) ||
        marks.containsAll(listOf(4,9,14,19,24))

    fun score() : Int{
        val finalNumber = board[marks.last()]
        val sumOfRemainingNumbers = board.filterIndexed { index, i -> !marks.contains(index) }.sum()
        return finalNumber*sumOfRemainingNumbers
    }
}

data class BingoGame(
    val drawnNumbers: List<Int>,
    val boards: List<BingoBoard>,
) {
    fun play(): BingoBoard? {
        drawnNumbers.forEach { currentNumber ->
            boards.forEach { it.mark(currentNumber) }
            val winnerBoard = boards.filter { it.hasWon() }.minByOrNull { it.score() }
            if(winnerBoard != null)
                return winnerBoard
        }
        return null
    }
}

fun parseGame(input: List<String>) = BingoGame(
    drawnNumbers = input[0].split(",").map { it.toInt() },
    boards = input
        .subList(1, input.size)
        .filter { it.isNotBlank() }
        .chunked(BINGO_BOARR_DIMENSION)
        .map { rows -> rows.joinToString(" ").split("""\s+""".toRegex()).map { it.toInt() } }
        .map { BingoBoard(it) }
)

fun part1(input: List<String>): Int {
    val game = parseGame(input)
    val winnerBoard = game.play()
    return winnerBoard?.score() ?: -1
}
fun part2(input: List<String>): Int { return 0 }

fun main() {
    println(part1(resource("Day04.txt")))
    println(part2(resource("Day04.txt")))
}