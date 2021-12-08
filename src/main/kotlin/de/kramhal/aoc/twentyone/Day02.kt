package de.kramhal.aoc.twentyone

/**
 * Now, you need to figure out how to pilot this thing.

It seems like the submarine can take a series of commands like forward 1, down 2, or up 3:

forward X increases the horizontal position by X units.
down X increases the depth by X units.
up X decreases the depth by X units.

Note that since you're on a submarine, down and up affect your depth, and so they have the opposite result of what you might expect.

The submarine seems to already have a planned course (your puzzle input). You should probably figure out where it's going. For example:

 */
object Day02{
    fun data() = resource("Day02.txt")

    enum class Direction { forward, down, up }
    data class MovementCommand(val direction: Direction, val distance: Int)
    class MovementTracker {
        var level: Int = 0
            get
        var position: Int = 0
            get

        fun command(it: MovementCommand) {
            when(it.direction) {
                Direction.forward -> position+=it.distance
                Direction.down -> level+=it.distance
                Direction.up -> level-=it.distance
            }
        }
    }

    fun parseCommand(command: String) = command.split(" ").let {
        MovementCommand(Direction.valueOf(it[0]), it[1].toInt())
    }

    fun part1(input: List<String>): Int {
        val tracker = MovementTracker()
        input.map { parseCommand(it) }.forEach { tracker.command(it) }
        return tracker.position*tracker.level
    }

    class AimingMovementTracker {
        var aim: Int = 0
            get
        var level: Int = 0
            get
        var position: Int = 0
            get

        fun command(it: MovementCommand) {
            when(it.direction) {
                Direction.forward -> {
                    position+=it.distance
                    level+=it.distance*aim
                }
                Direction.down -> aim+=it.distance
                Direction.up -> aim-=it.distance
            }
        }
    }

    fun part2(input: List<String>): Int {
        val tracker = AimingMovementTracker()
        input.map { parseCommand(it) }.forEach { tracker.command(it) }
        return tracker.position*tracker.level
    }

}

fun main() {
    println(Day02.part1(Day02.data()))
    println(Day02.part2(Day02.data()))
}