package aoc.days

import aoc.utils.Point2D
import aoc.utils.Vec2
import kotlin.math.abs


class Day12 {
    val inputs: List<String>
    val instructions: List<Instruction>

    constructor(inputs: List<String>) {
        this.inputs = inputs
        instructions = inputs.map { Instruction(it[0], it.substring(1 until it.length).toInt()) }
    }

    fun solvePart1():Int {
        var direction = Vec2(1,0)
        var position = Point2D(0, 0)
        instructions.forEach {
            //println("Before: $it $position $direction")
            when(it.move) {
                'F' -> position += direction * it.length
                'L' -> {
                    direction.rotateDeg(-it.length).also { direction = it }
                }
                'R' -> {
                    direction.rotateDeg(it.length).also { direction = it }
                }
                'N' -> position += Vec2.up()*it.length
                'E' -> position += Vec2.right()*it.length
                'S' -> position += Vec2.down()*it.length
                'W' -> position += Vec2.left()*it.length
            }
            //println("after: $it $position $direction")
        }
        println("$position $direction")
        return abs(position.x) + abs(position.y)
    }

    fun solvePart2():Int {
        var wp = Vec2(10, -1)
        var position = Point2D(0, 0)
        instructions.forEach {
            //println("Before: $it $position $wp")
            when(it.move) {
                'F' -> position += wp * it.length
                'L' -> wp.rotateDeg(-it.length).also { wp = it }
                'R' -> wp.rotateDeg(it.length).also { wp = it }
                'N' -> wp += Vec2.up()*it.length
                'E' -> wp += Vec2.right()*it.length
                'S' -> wp += Vec2.down()*it.length
                'W' -> wp += Vec2.left()*it.length
            }
            //println("after: $it $position $direction")
        }
        println("$position $wp")
        return abs(position.x) + abs(position.y)
    }

    data class Instruction(val move: Char, val length: Int)
}