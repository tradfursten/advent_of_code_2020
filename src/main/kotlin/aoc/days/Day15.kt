package aoc.days

class Day15(inputs: String) {
    val numbers = mutableListOf<Int>()
    val memory = mutableMapOf<Int, Int>()

    init {
        inputs.split(",").map { it.toInt() }.forEach { numbers.add(it) }
        numbers.forEachIndexed { index, i ->
            memory[i] = index
        }
    }

    fun solvePart1():Int {
        for (i in numbers.size+1..2020) {
            var next: Int
            val prev = numbers.last()
            val prevPrev = memory.getOrDefault(prev, -1)
            memory[prev] = numbers.size - 1
            if (prevPrev == -1) {
                next = 0
            } else {
                next = numbers.size - 1 - prevPrev
            }
            numbers.add(next)
        }
        return numbers.last()
    }

    fun solvePart2():Int {
        for (i in numbers.size+1..30000000) {
            var next: Int
            val prev = numbers.last()
            val prevPrev = memory.getOrDefault(prev, -1)
            memory[prev] = numbers.size - 1
            if (prevPrev == -1) {
                next = 0
            } else {
                next = numbers.size - 1 - prevPrev
            }
            numbers.add(next)
        }
        return numbers.last()
    }
}