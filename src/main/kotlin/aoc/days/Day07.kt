package aoc.days

import java.util.ArrayDeque as ArrayDeque

class Day07 {
    val inputs: List<String>
    private val rules: MutableMap<String, MutableList<Pair<String, Int>>> = mutableMapOf()
    private val rules2: MutableMap<String, MutableList<Pair<String, Int>>> = mutableMapOf()

    constructor(inputs: List<String>) {
        this.inputs = inputs
        inputs.forEach { line ->
            val parts = line.split(" ")
            val current = "${parts[0]} ${parts[1]}"
            val children = mutableListOf<Pair<String, Int>>()
            when {
                !line.contains("contain no other bag") -> {
                    for(i in 4 until parts.size step 4) {
                        val key = "${parts[i + 1]} ${parts[i + 2]}"
                        if (!rules.containsKey(key)) rules[key] = mutableListOf()
                        rules[key]?.add(current to parts[i].toInt())
                        children.add(key to parts[i].toInt())
                    }
                }
            }
            rules2[current] = children
        }
    }

    fun solvePart1(bag: String):Int {
        val queue = ArrayDeque<String>().also { it.add(bag) }
        val visited = mutableSetOf<String>().also { it.add(bag) }
        var nrBags = 0
        while (queue.isNotEmpty()) {
            val current = queue.pop()
            if (!visited.contains(current)) nrBags++
            visited.add(current)
            if (rules[current] == null) {
            } else {
                rules[current]?.filter { !visited.contains(it)}
                    ?.forEach {
                        queue.add(it.first)
                    }
            }
        }
        return nrBags
    }

    fun solvePart2(bag: String):Int = getBags(bag) - 1

    private fun getBags(bag: String): Int {
        return 1 + rules2[bag]!!.fold(0, { acc, rule ->
            acc + rule.second * getBags(rule.first)
        })
    }


}