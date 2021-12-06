package de.kramhal.aoc.twentyone

import resource

/**
 * As the submarine drops below the surface of the ocean, it automatically performs a sonar sweep of the nearby sea
 * floor. On a small screen, the sonar sweep report (your puzzle input) appears: each line is a measurement of the sea
 * floor depth as the sweep looks further and further away from the submarine.
 */
object Day01{
    fun data() = resource("Day01.txt")

    /**
     * The first order of business is to figure out how quickly the depth increases, just so you know what you're
     * dealing with - you never know if the keys will get carried into deeper water by an ocean current or a fish or
     * something.
     *
     * To do this, count the number of times a depth measurement increases from the previous measurement.
     * (There is no measurement before the first measurement.)
     */
    fun part1(input: List<String>): Int {
        val measurements = input.map { it.toInt() }
        return countIncreasedDepths(measurements)
    }

    /**
     * Considering every single measurement isn't as useful as you expected: there's just too much noise in the data.
     * Instead, consider sums of a three-measurement sliding window.
     *
     * Your goal now is to count the number of times the sum of measurements in this sliding window increases from the
     * previous sum. So, compare A with B, then compare B with C, then C with D, and so on. Stop when there aren't
     * enough measurements left to create a new three-measurement sum.
     */
    fun part2(input: List<String>): Int {
        val measurements = input.map { it.toInt() }
        val slidingWindows = (1 until measurements.size-1).map {
            measurements[it-1]+measurements[it]+measurements[it+1]
        }
        return countIncreasedDepths(slidingWindows)
    }

    private fun countIncreasedDepths(measurements: List<Int>): Int {
        return measurements.foldIndexed(0) { index, acc, currentValue ->
            if (index == 0) 0
            else if (measurements[index - 1] < currentValue) acc + 1
            else acc
        }
    }
}

fun main() {
    println(Day01.part1(Day01.data()))
    println(Day01.part2(Day01.data()))
}