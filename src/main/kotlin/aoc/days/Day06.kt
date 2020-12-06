package aoc.days

class Day06 {
    val inputs: List<String>
    private val groups: List<List<String>>

    constructor(inputs: List<String>) {
        this.inputs = inputs
        groups = mutableListOf()
        var currentCroup = mutableListOf<String>()
        for(i in inputs) {
            if (i.isBlank()) {
                groups.add(currentCroup)
                currentCroup = mutableListOf<String>()
            } else {
                currentCroup.add(i)
            }
        }
        groups.add(currentCroup)
    }

    fun solvePart1():Int {
        return groups.map{ group ->
            group.fold(mutableSetOf<Char>(), { a, b ->
                a.addAll(b.asIterable())
                a
            }).size
        }.fold(0, {a,b -> a+b})
    }

    fun solvePart2():Int {
        return groups.map{ group ->
            val allYes = mutableSetOf<Char>().also {
                it.addAll(group.first().asIterable())
            }
            group.forEach { person ->
                allYes.removeIf { it !in person }
            }
            allYes.size
        }.fold(0, {a,b -> a+b})
    }
}