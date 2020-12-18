package aoc.days

import aoc.days.CellState.ACTIVE
import aoc.days.CellState.INACTIVE
import aoc.utils.Point3D
import aoc.utils.Point4D

class Day17 {
    val inputs: List<String>
    val cells = mutableMapOf<Point4D, Pair<CellState, CellState>>()
    constructor(inputs: List<String>) {
        this.inputs = inputs
        inputs.forEachIndexed {y, s ->
            s.forEachIndexed { x, c ->
                cells[Point4D(x, y, 0, 0)] = when(c) {
                    '.' -> INACTIVE to INACTIVE
                    '#' -> ACTIVE to ACTIVE
                    else -> throw IllegalThreadStateException("Unkown char")
                }
            }
        }
    }

    private fun step(step: Int) {
        //println("Do step: $step")
        val activeCells = cells.filter { it.value.first == ACTIVE }
        val xMin = activeCells.map { it.key.x }.minOf { it } - 1
        val xMax = activeCells.map { it.key.x }.maxOf { it } + 1

        val yMin = activeCells.map { it.key.y }.minOf { it } - 1
        val yMax = activeCells.map { it.key.y }.maxOf { it } + 1

        val zMin = activeCells.map { it.key.z }.minOf { it } - 1
        val zMax = activeCells.map { it.key.z }.maxOf { it } + 1

        for (z in zMin..zMax) {
            for (y in yMin..yMax) {
                for (x in xMin..xMax) {
                    val p = Point4D(x, y, z, 0)
                    val c = cells.getOrDefault(p, INACTIVE to INACTIVE)
                    when(c.first) {
                        ACTIVE -> {
                            val count = cells.filter { it.key in p.neighbours() }
                                    .filter { it.key.w == 0 }
                                    .filter { it.value.first == ACTIVE }.count()
                            if (!(count == 2 || count == 3)) {
                                cells[p] = c.first to INACTIVE
                            }
                        }
                        INACTIVE -> {
                            val count = cells.filter { it.key in p.neighbours() }
                                    .filter { it.key.w == 0 }
                                    .filter { it.value.first == ACTIVE }.count()
                            if (count == 3) {
                                cells[p] = c.first to ACTIVE
                            }
                        }
                    }

                }
            }
        }
        cells.forEach {
            if(it.value.first != it.value.second) {
                cells[it.key] = it.value.second to it.value.second
            }
        }
        //print()
    }

    private fun step2(step: Int) {
        println("Do step: $step")
        val activeCells = cells.filter { it.value.first == ACTIVE }
        val xMin = activeCells.map { it.key.x }.minOf { it } - 1
        val xMax = activeCells.map { it.key.x }.maxOf { it } + 1

        val yMin = activeCells.map { it.key.y }.minOf { it } - 1
        val yMax = activeCells.map { it.key.y }.maxOf { it } + 1

        val zMin = activeCells.map { it.key.z }.minOf { it } - 1
        val zMax = activeCells.map { it.key.z }.maxOf { it } + 1

        val wMin = activeCells.map { it.key.w }.minOf { it } - 1
        val wMax = activeCells.map { it.key.w }.maxOf { it } + 1

        for(w in wMin..wMax) {
            for (z in zMin..zMax) {
                for (y in yMin..yMax) {
                    for (x in xMin..xMax) {
                        val p = Point4D(x, y, z, w)
                        val c = cells.getOrDefault(p, INACTIVE to INACTIVE)
                        when (c.first) {
                            ACTIVE -> {
                                val count = cells.filter { it.key in p.neighbours() }
                                        .filter { it.value.first == ACTIVE }.count()
                                if (!(count == 2 || count == 3)) {
                                    cells[p] = c.first to INACTIVE
                                }
                            }
                            INACTIVE -> {
                                val count = cells.filter { it.key in p.neighbours() }
                                        .filter { it.value.first == ACTIVE }.count()
                                if (count == 3) {
                                    cells[p] = c.first to ACTIVE
                                }
                            }
                        }

                    }
                }
            }
        }
        cells.forEach {
            if(it.value.first != it.value.second) {
                cells[it.key] = it.value.second to it.value.second
            }
        }
        //print()
    }

    fun step2b(step: Int) {
        println("Do step $step")
        cells.filter { it.value.first == ACTIVE }
                .map { it.key.neighbours() }
                .flatten()
                .distinct()
                .forEach { p ->
                    val c = cells.getOrDefault(p, INACTIVE to INACTIVE)
                    when (c.first) {
                        ACTIVE -> {
                            val count = cells.filter { it.key in p.neighbours() }
                                    .filter { it.value.first == ACTIVE }.count()
                            if (!(count == 2 || count == 3)) {
                                cells[p] = c.first to INACTIVE
                            }
                        }
                        INACTIVE -> {
                            val count = cells.filter { it.key in p.neighbours() }
                                    .filter { it.value.first == ACTIVE }.count()
                            if (count == 3) {
                                cells[p] = c.first to ACTIVE
                            }
                        }
                    }
                }
        cells.forEach {
            if(it.value.first != it.value.second) {
                cells[it.key] = it.value.second to it.value.second
            }
        }
    }

    fun solvePart1(steps: Int):Int {
        //print()
        for (i in 1..steps) {
            step(i)
        }
        return cells.filter { it.value.first == ACTIVE }.count()
    }

    fun solvePart2(steps: Int):Int {
        for (i in 1..steps) {
            step2(i)
        }
        return cells.filter { it.value.first == ACTIVE }.count()

    }

    private fun print() {
        val activeCells = cells.filter { it.value.first == ACTIVE }
        val xMin = activeCells.map { it.key.x }.minOf { it } - 1
        val xMax = activeCells.map { it.key.x }.maxOf { it } + 1

        val yMin = activeCells.map { it.key.y }.minOf { it } - 1
        val yMax = activeCells.map { it.key.y }.maxOf { it } + 1

        val zMin = activeCells.map { it.key.z }.minOf { it } - 1
        val zMax = activeCells.map { it.key.z }.maxOf { it } + 1

        for (z in zMin..zMax) {
            println("z: $z")
            for (y in yMin..yMax) {
                for (x in xMin..xMax) {
                    val c = cells.getOrDefault(Point3D(x, y, z), INACTIVE to INACTIVE)
                    print(when (c.first) {
                        INACTIVE -> '.'
                        ACTIVE -> '#'
                    })
                }
                print("\n")
            }
        }
    }

}

enum class CellState{
    ACTIVE, INACTIVE
}