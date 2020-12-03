package aoc.days

import java.io.Serializable
import java.lang.IllegalStateException

class Day03 {

    val input: List<String>
    val forest: List<CircularList<Serializable>>

    constructor(input: List<String>) {
        this.input = input
        this.forest = input.map {
            it.map { c ->
                when (c) {
                    '.' -> AREA.OPEN
                    '#' -> AREA.TREES
                    else -> IllegalStateException("Unknown area type")
                }
            }.circular()
        }
    }

    fun solvePart1(): Int {
        return traverse(3, 1)
    }

    fun solvePart2(): Long {
        val r = listOf(
                traverse(1, 1),
                traverse(3, 1),
                traverse(5, 1),
                traverse(7, 1),
                traverse(1, 2)
        )
        println("Traversals $r")

        return r.fold(1L, { acc: Long, i: Int -> acc * i })

    }

    private fun traverse(xAmount: Int, yAmount: Int):Int {
        var x = 0
        var y = 0
        var trees = 0
        while (y < forest.size) {
            when (forest[y][x]) {
                AREA.TREES -> trees++
            }
            x += xAmount
            y += yAmount
        }
        return trees
    }

}

class CircularList<out T>(private val list: List<T>): List<T> by list {

    override fun get(index: Int): T = list[index.safe()]

    private fun Int.safe(): Int = if (this < 0 )(this + size % size) else this % size

}
fun <T> List<T>.circular(): CircularList<T> = CircularList(this)

enum class AREA {
    OPEN, TREES
}