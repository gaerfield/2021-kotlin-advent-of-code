package de.kramhal.aoc.twentyone.day03

import de.kramhal.aoc.twentyone.resource

class DiagnosticsRecorder(private val measurements: List<String>) {
    private val charlists by lazy { rotate(measurements) }

    private fun rotate(measurements: List<String>): List<MutableList<Boolean>> {
        val result = List(measurements.first().length) { mutableListOf<Boolean>() }

        measurements.forEach { s ->
            s.forEachIndexed { index, c ->
                if (c == '0') result[index].add(false)
                else result[index].add(true)
            }
        }
        return result
    }

    private fun List<MutableList<Boolean>>.countsOfTruesAndFalses() =
        map { list -> list.partition { it } }
        .map { (listOfTrues, listOfFalses) -> Pair(listOfTrues.size, listOfFalses.size) }

    private fun List<MutableList<Boolean>>.mostCommonBitField() = countsOfTruesAndFalses()
        .map { (trueCount, falseCount) -> if(trueCount > falseCount) '1' else '0' }
        .joinToString("")

    private fun List<MutableList<Boolean>>.leastCommonBitField() = mostCommonBitField()
        .map { if(it == '1') '0' else '1' }
        .joinToString("")

    internal fun gamma() = charlists.mostCommonBitField().binaryAsInt()
    internal fun epsilon() = charlists.leastCommonBitField().binaryAsInt()
    fun powerConsumption() = gamma()*epsilon()



    enum class FilterStrategy { MostCommonBit, LeastCommonBit }
    private fun findRating(
        filterStrategy: FilterStrategy,
        remainingMeasurements: List<String> = measurements,
        index: Int = 0,
    ): Int {
        val (trueCount, falseCount) = rotate(remainingMeasurements).countsOfTruesAndFalses()[index]
        val filter = when (filterStrategy) {
            FilterStrategy.MostCommonBit -> if (trueCount >= falseCount) '1' else '0'
            FilterStrategy.LeastCommonBit -> if (trueCount >= falseCount) '0' else '1'
        }
        val newRemaining = remainingMeasurements.filter { it[index] == filter }
        if (newRemaining.isEmpty())
            throw IllegalStateException()
        if (newRemaining.size == 1)
            return newRemaining[0].binaryAsInt()
        return findRating(filterStrategy = filterStrategy, remainingMeasurements = newRemaining, index = index + 1)
    }

    fun oxygenGeneratorRating() : Int {
        return findRating(FilterStrategy.MostCommonBit)
    }

    fun co2ScrubberRating() : Int {
        return findRating(FilterStrategy.LeastCommonBit)
    }

    internal fun lifeSupportRating() = oxygenGeneratorRating() * co2ScrubberRating()

    private fun String.binaryAsInt(): Int = Integer.parseInt(this, 2)

}

fun part1(input: List<String>): Int {
    val recorder = DiagnosticsRecorder(input)
    return recorder.powerConsumption()
}

fun part2(input: List<String>): Int {
    return DiagnosticsRecorder(input).lifeSupportRating()
}

fun main() {
    println(part1(resource("Day03.txt")))
    println(part2(resource("Day03.txt")))
}