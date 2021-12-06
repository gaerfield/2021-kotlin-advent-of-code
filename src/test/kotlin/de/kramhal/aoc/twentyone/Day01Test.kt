package de.kramhal.aoc.twentyone

import kotlin.test.Test
import kotlin.test.assertEquals

internal class Day01Test {

    /**
     * This report indicates that, scanning outward from the submarine, the sonar sweep found depths of 199, 200, 208, 210, and so on.
     */
    val testData = listOf("199","200","208","210","200","207","240","269","260","263")

    /**
     * In the example above, the changes are as follows:
     * 199 (N/A - no previous measurement)
     * 200 (increased)
     * 208 (increased)
     * 210 (increased)
     * 200 (decreased)
     * 207 (increased)
     * 240 (increased)
     * 269 (increased)
     * 260 (decreased)
     * 263 (increased)
     *
     * In this example, there are 7 measurements that are larger than the previous measurement.
     *
     * How many measurements are larger than the previous measurement?
     */
    @Test
    fun part1() { assertEquals(7, Day01.part1(testData)) }

    /**
     * Again considering the above example:
     * Start by comparing the first and second three-measurement windows. The measurements in the first window are
     * marked A (199, 200, 208); their sum is 199 + 200 + 208 = 607. The second window is marked B (200, 208, 210); its
     * sum is 618. The sum of measurements in the second window is larger than the sum of the first, so this first
     * comparison increased.
     */
    @Test
    fun part2() { assertEquals(5, Day01.part2(testData)) }
}