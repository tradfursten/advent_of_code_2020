package aoc.days.old

import aoc.utils.Point2D
import java.util.ArrayDeque


class Day18_2019(val input: List<String>) {

    fun solvePart1():Int {
        val maze = Maze.from(input)
        return maze.minimumSteps()
    }


    fun solvePart2():Int {
        val maze = Maze.from(input)
        return maze.minimumSteps()
    }


    class Maze (
        val start: Set<Point2D>,
        val openSpaces: Set<Point2D>,
        val doors: Map<Point2D, Char>,
        val keys: Map<Point2D, Char>
    ) {
        companion object {
            fun from(input: List<String>):Maze {
                var start = mutableSetOf<Point2D>()
                val openSpace = mutableSetOf<Point2D>()
                val doors = mutableMapOf<Point2D, Char>()
                val keys = mutableMapOf<Point2D, Char>()

                input.forEachIndexed { y, row ->
                    row.forEachIndexed { x, c ->
                        val place = Point2D(x, y)
                        if (c == '@') start.add(place)
                        if (c != '#') openSpace.add(place)
                        if (c in 'A'..'Z') doors[place] = c
                        if (c in 'a'..'z') keys[place] = c
                    }
                }

                return Maze(start, openSpace, doors, keys)
            }

        }


        fun minimumSteps(from: Set<Point2D> = start,
                         haveKeys: Set<Char> = mutableSetOf(),
                         seen: MutableMap<Pair<Set<Point2D>, Set<Char>>, Int> = mutableMapOf()
                         ): Int {
            val state = Pair(from, haveKeys)

            if (state in seen) return seen.getValue(state)

            val answer = findReachableFrom(from, haveKeys).map { entry ->
                val (at, dist, point) = entry.value
                dist + minimumSteps((from - point) + at, haveKeys + entry.key, seen)
            }.minOrNull() ?: 0
            seen[state] = answer
            return answer
        }

        private fun findReachableFrom(from: Set<Point2D>, haveKeys: Set<Char>): Map<Char, Triple<Point2D, Int, Point2D>> =
                from.map { point ->
                    findReachableKeys(point, haveKeys).map { entry ->
                        entry.key to Triple(entry.value.first, entry.value.second, point)
                    }
                 }.flatten().toMap()

        private fun findReachableKeys(
                from: Point2D,
                haveKeys: Set<Char> = mutableSetOf()
        ): Map<Char, Pair<Point2D, Int>> {
            val queue = ArrayDeque<Point2D>().apply { add(from) }
            val distance = mutableMapOf(from to 0)
            val keysDistance = mutableMapOf<Char, Pair<Point2D, Int>>()

            while (queue.isNotEmpty()){
                val next = queue.pop()
                next.neighbors()
                        .filter { it in openSpaces }
                        .filterNot { it in distance }
                        .forEach { point ->
                            distance[point] = distance[next]!! + 1
                            val door = doors[point]
                            val key = keys[point]
                            if (door == null || door.toLowerCase() in haveKeys) {
                                if (key != null && key !in haveKeys) {
                                    keysDistance[key] = Pair(point, distance[point]!!)
                                } else {
                                    queue.add(point)
                                }
                            }
                        }
            }
            return keysDistance
        }
    }
}