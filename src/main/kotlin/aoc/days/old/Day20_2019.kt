package aoc.days.old

import aoc.utils.Point2D
import aoc.utils.getOrNull
import java.util.ArrayDeque

class Day20_2019(val input: List<String>) {

    fun solvePart1() {
        val donut = Donut.from(input)
        println(donut.breadthFirstSearch())

    }

    fun solvePart2() {
        val donut = Donut.from(input)
        println(donut.breadthFirstSearchLevels())
    }

    class Donut (
            val path: Set<Point2D>,
            val unlinkedPortals: Map<String, Point2D>,
            val portals: Map<Point2D, Point2D>,
            val outwardsX: Set<Int>,
            val outwardsY: Set<Int>
    )
    {
        companion object {
            fun from(input: List<String>):Donut {
                val path = mutableSetOf<Point2D>()
                val unlinkedPortals = mutableMapOf<String, Point2D>()
                val portals = mutableMapOf<Point2D, Point2D>()
                input.forEachIndexed { y, row ->
                    row.forEachIndexed { x, c ->
                        val at = Point2D(x, y)
                        if (c == '.') {
                            path.add(at)
                        }

                    }
                }
                input.forEachIndexed { y, row ->
                    row.forEachIndexed { x, c ->
                        val at = Point2D(x, y)
                        var name: String? = null
                        var portal: Point2D? = null
                        if (c in 'A'..'Z') {
                            when {
                                input.getOrNull(at.up()) in 'A'..'Z' && at.down() in path -> {
                                    name = "$c${input.getOrNull(at.up())}"
                                    portal = at.down()
                                }
                                input.getOrNull(at.down()) in 'A'..'Z' && at.up() in path -> {
                                    name = "${input.getOrNull(at.down())}$c"
                                    portal = at.up()
                                }
                                input.getOrNull(at.left()) in 'A'..'Z' && at.right() in path -> {
                                    name = "${input.getOrNull(at.left())}$c"
                                    portal = at.right()
                                }
                                input.getOrNull(at.right()) in 'A'..'Z' && at.left() in path -> {
                                    name = "$c${input.getOrNull(at.right())}"
                                    portal = at.left()
                                }

                            }
                            if (name != null && portal != null) {
                                if (name in unlinkedPortals) {
                                    portals[portal] = unlinkedPortals[name]!!
                                    portals[unlinkedPortals[name]!!] = portal
                                    unlinkedPortals.remove(name)
                                } else {
                                    unlinkedPortals[name] = portal
                                }
                            }
                        }
                    }
                }
                return Donut(path, unlinkedPortals, portals, setOf(2, path.maxOf { it.x }), setOf(2, path.maxOf { it.y } ))
            }
        }
        fun breadthFirstSearch(start: Point2D = unlinkedPortals["AA"]!!,
                               end: Point2D = unlinkedPortals["ZZ"]!!
        ): Int {
            val discovered = mutableSetOf<Point2D>().apply {
                add(start)
            }

            val queue = ArrayDeque<MutableList<Point2D>>().apply {
                add(mutableListOf(start))
            }

            while (queue.isNotEmpty()) {
                val path = queue.pop()
                if (path.first() == end) return path.size - 1

                path.first().neighboursWithPortals()
                        .filter { it !in discovered }
                        .forEach{ neighbour ->
                            discovered.add(neighbour)
                            queue.addLast(mutableListOf<Point2D>()
                                    .apply { this.addAll(path) }
                                    .also { it.add(0, neighbour) })
                }
            }
            throw IllegalStateException("No path to end")
        }

        private fun Point2D.neighboursWithPortals(): List<Point2D> {
            val neighbours = this.neighbors().filter { it in path }
            return (neighbours + portals[this]).filterNotNull()
        }

        fun breadthFirstSearchLevels(start: Point2D = unlinkedPortals["AA"]!!,
                               end: Point2D = unlinkedPortals["ZZ"]!!
        ): Int {
            val discovered = mutableSetOf<Pair<Point2D, Int>>().apply {
                add(start to 0)
            }

            val queue = ArrayDeque<MutableList<Pair<Point2D, Int>>>().apply {
                add(mutableListOf(start to 0))
            }

            while (queue.isNotEmpty()) {
                val path = queue.pop()
                if (path.first() == end to 0) return path.size - 1

                path.first().neighboursWithPortalsLevels().filter { it !in discovered }.forEach{ neighbour ->
                    discovered.add(neighbour)
                    queue.addLast(mutableListOf<Pair<Point2D, Int>>()
                            .apply { this.addAll(path) }
                            .also { it.add(0, neighbour) })
                }
            }
            throw IllegalStateException("No path to end")
        }

        private fun Pair<Point2D, Int>.neighboursWithPortalsLevels(): List<Pair<Point2D, Int>> {
            val neighbours = this.first.neighbors().filter { it in path }.map { it to this.second }.toMutableList()
            portals[this.first]?.let {
                val levelDir = if (this.first.x in outwardsX || this.first.y in outwardsY) -1 else 1
                neighbours.add(it to this.second + levelDir)
            }
            return neighbours.filter { it.second >= 0 }
        }
    }

}