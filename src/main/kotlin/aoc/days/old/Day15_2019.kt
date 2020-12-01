package aoc.days.old

import aoc.utils.Point2D
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import java.lang.IllegalStateException
import java.util.ArrayDeque

class Day15_2019(val input: String) {
    val computer: IntCode = IntCode(input)

    fun solvePart1():IntCode {

        runBlocking {
            val computerJob = async {

                computer.run()
            }
            val maze = calculateMaze().also {
                computerJob.cancel()
            }
            printMaze(maze)
            val oxygenLocation = maze.entries.first { it.value == oxygen }.key
            val res = findPaths(Point2D.ORIGIN, oxygenLocation) { maze.getOrDefault(it, wall) != wall }
            println(res.first().size - 1)
        }
        //println(computer.program)
        return computer
    }

    private fun printMaze(maze: MutableMap<Point2D, Char>) {
        val xMax = maze.maxOf { it.key.x }
        val xMin = maze.minOf { it.key.x }

        val yMax = maze.maxOf { it.key.y }
        val yMin = maze.minOf { it.key.y }
        for(y in yMin until yMax+1) {
            for (x in xMin until xMax+1) {
                print(maze.getOrDefault(Point2D(x, y), ' '))
            }
            print("\n")
        }
    }

    private fun findPaths(from: Point2D,
                          to: Point2D?,
                          validNeighbor: (Point2D) -> Boolean): Sequence<List<Point2D>> = sequence {
        val visited = mutableSetOf<Point2D>()
        val queue = ArrayDeque<MutableList<Point2D>>().apply { add(mutableListOf(from))}
        while (queue.isNotEmpty()) {
            val currentPath = queue.pop()
            if (currentPath.last() == to) {
                yield(currentPath)
            }

            if (currentPath.last() !in visited) {
                visited.add(currentPath.last())
                val neighbours = currentPath.last().neighbors()
                        .filter { validNeighbor(it) }
                        .filter { it !in visited }

                if(neighbours.isEmpty() && to == null) {
                    yield(currentPath)
                }

                neighbours.forEach { neighbor ->
                            queue.add(mutableListOf<Point2D>()
                                    .also { it.addAll(currentPath) }
                                    .apply { add(neighbor) })
                        }
            }

        }
    }

    private suspend fun calculateMaze(at: Point2D = Point2D.ORIGIN,
                      mazeSoFar: MutableMap<Point2D,Char> = mutableMapOf(Point2D.ORIGIN to start)): MutableMap<Point2D, Char> {
        //println("at $at, looking at neighbours")
        at.neighbors().filterNot { it in mazeSoFar }
                .forEach{neighbour ->
                    if (neighbour.x == 22) println("at edge")
                    computer.input.send(at.getDirection(neighbour))
                    when(val received = computer.output.receive()) {
                        robotFoundOxygen, robotMoved -> {
                            if (received == robotFoundOxygen) println("Found oxygen at $at")
                            mazeSoFar[neighbour] = if (received == robotMoved) open else oxygen
                            calculateMaze(neighbour, mazeSoFar)
                            computer.input.send(neighbour.getDirection(at))
                            computer.output.receive()
                        }
                        robotHitWall -> mazeSoFar[neighbour] = wall
                    }
                }
        return mazeSoFar
    }

    fun solvePart2() {
        runBlocking {
            val computerJob = async {

                computer.run()
            }
            val maze = calculateMaze().also {
                computerJob.cancel()
            }
            printMaze(maze)
            val oxygenLocation = maze.entries.first { it.value == oxygen }.key
            val res = findPaths(oxygenLocation, null) { maze.getOrDefault(it, wall) != wall }
            println(res.last().size - 1)
        }
        //println(computer.program)
    }

    companion object {
        val robotHitWall = 0L
        val robotMoved = 1L
        val robotFoundOxygen = 2L
        val open = '.'
        val wall = '#'
        val oxygen = 'o'
        val start = 's'
    }

    fun Point2D.getDirection(to: Point2D): Long {
        return when (to) {
            up() -> 1
            down() -> 2
            right() -> 3
            left() -> 4
            else -> throw IllegalStateException("Invalid neighbour $to")
        }
    }
}