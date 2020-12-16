package aoc.days

import aoc.utils.Point2D
import aoc.utils.Vec2


class Day11 {
    val inputs: List<String>
    val seats: Seats
    constructor(inputs: List<String>) {
        this.inputs = inputs
        seats = Seats()
        inputs.forEachIndexed { x, s ->
            s.forEachIndexed { y, c ->
                seats.addTile(Point2D(x, y), c)
                seats.maxY = y
            }
            seats.maxX = x
        }
    }

    fun solvePart1():Int {
        var c = 0
        do {
            c = seats.step()
        } while (c != 0)
        return seats.occupied()
    }

    fun solvePart2():Int {
        var c = 0
        do {
            c = seats.stepRayCast()
        } while (c != 0)
        return seats.occupied()
    }
}

class Seats {
    val minX = 0
    val minY = 0
    var maxX = 0
    var maxY = 0

    fun addTile(point2D: Point2D, c: Char) {
        seats[point2D] = Pair(SeatState.parse(c), SeatState.parse(c))
    }

    override fun toString(): String {
        var s = StringBuilder()
        for (x in minX .. maxX) {
            for (y in minY .. maxY ) {
                s.append(seats[Point2D(x, y)]?.first.toString())
            }
            s.append('\n')
        }
        return s.toString()
    }

    fun step(): Int {
        seats.entries.filter { it.value.first != SeatState.FLOOR }.forEach {
            val surrounding = it.key.neighborsDiagonal().map { s -> seats[s] }.filter { s -> s?.first == SeatState.OCCUPIED }.count()
            when(it.value.first) {
                SeatState.EMPTY -> {
                    if (surrounding == 0) {
                        seats[it.key] = Pair(it.value.first, SeatState.OCCUPIED)
                    }
                }
                SeatState.OCCUPIED -> {
                    if (surrounding >= 4) {
                        seats[it.key] = Pair(it.value.first, SeatState.EMPTY)
                    }
                }
            }
        }
        var changed = 0
        seats.entries.filter { it.value.first != it.value.second }
                .forEach {
                    changed++
                    seats[it.key] = Pair(it.value.second, it.value.second)
                }
        return changed
    }

    fun stepRayCast(): Int {
        seats.entries.filter { it.value.first != SeatState.FLOOR }
                .forEach {
                    val surrounding = Vec2.directionsDiagonal
                            .map { d -> castRay(it.key, d) }
                            .filter { s -> s == SeatState.OCCUPIED}
                            .count()
                    when(it.value.first) {
                        SeatState.EMPTY -> {
                            if (surrounding == 0) {
                                seats[it.key] = Pair(it.value.first, SeatState.OCCUPIED)
                            }
                        }
                        SeatState.OCCUPIED -> {
                            if (surrounding >= 5) {
                                seats[it.key] = Pair(it.value.first, SeatState.EMPTY)
                            }
                        }
                    }

                }
        var changed = 0
        seats.entries.filter { it.value.first != it.value.second }
                .forEach {
                    changed++
                    seats[it.key] = Pair(it.value.second, it.value.second)
                }
        return changed
    }

    private fun castRay(p: Point2D, v: Vec2): SeatState {
        var tempP = p.copy()
        do {
            tempP += v
            val s = seats.getOrDefault(tempP, Pair(SeatState.FLOOR, SeatState.FLOOR))
            if (s.first != SeatState.FLOOR) {
                return s.first
            }
        } while (tempP.x in minX..maxX && tempP.y in minY..maxY)
        return SeatState.FLOOR
    }

    fun occupied(): Int = seats.entries.filter { it.value.first == SeatState.OCCUPIED }.count()


    private val seats = mutableMapOf<Point2D, Pair<SeatState, SeatState>>()
}

enum class SeatState {
    FLOOR, EMPTY, OCCUPIED;
    companion object {
        fun parse(c: Char): SeatState =
            when(c) {
               '.' -> FLOOR
               'L' -> EMPTY
               else -> FLOOR
            }
    }

    override fun toString(): String {
        return when(this) {
            FLOOR -> "."
            EMPTY -> "L"
            OCCUPIED -> "#"
        }
    }
}