package aoc.days

import aoc.utils.pow

val acc = mutableListOf<Int>()

class Day10 {

    private val adapters: List<Int>

    constructor(inputs: List<String>) {
        val a = inputs.map { it.toInt() }.sorted().toMutableList()
        a.add(0,0)
        a.add(a.last()+3)
        adapters = a
    }

    fun solvePart1():Int {
        var oneStep = 0
        var threeStep = 0
        for (i in 0 until adapters.size - 1) {
            when (adapters[i+1] - adapters[i]) {
                1 -> oneStep++
                3 -> threeStep++
            }
        }
        return oneStep * (threeStep+1)

    }

    fun solvePart2(): Long {
        val dist = mutableListOf<Int>()
        for(i in 0 until adapters.size-1) {
                dist.add(adapters[i+1] - adapters[i])
        }
        val ones = mutableMapOf<Int, Int>()
        var curr = 0
        for (i in 0 until dist.size) {
            if (dist[i] == 1) {
                curr++
            } else {
                if(!ones.containsKey(curr)){
                    ones[curr] = 0
                }
                ones[curr] = ones[curr]?.plus(1)!!
                curr = 0
            }
        }
        println(dist)
        println(ones)
        return 7.pow(ones.getOrDefault(4,0)) * 4.pow(ones.getOrDefault(3, 0)) * 2.pow(ones.getOrDefault(2, 0))
    }
}
