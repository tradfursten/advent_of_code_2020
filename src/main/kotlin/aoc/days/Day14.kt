package aoc.days

class Day14 {
    val inputs: List<String>
    val memory = mutableMapOf<Long, Long>()
    constructor(inputs: List<String>) {
        this.inputs = inputs
    }

    fun solvePart1():Long {
        var mask = DEFAULT_MASK
        for(i in inputs) {
            val parts = i.split(" ", "[","]")
            when (parts[0]) {
                "mask" -> mask = parts[2]
                "mem" -> {
                    memory[parts[1].toLong()] = parts[4].maskWith(mask)
                }
            }
        }
        return memory.entries.map { it.value }.sum()
    }

    fun solvePart2():Long {
        var mask = DEFAULT_MASK
        for(i in inputs) {
            val parts = i.split(" ", "[","]")
            when (parts[0]) {
                "mask" -> mask = parts[2]
                "mem" -> {
                    parts[1].getMemoryAdresses(mask).forEach {
                        memory[it] = parts[4].toLong()
                    }
                }
            }
        }
        return memory.entries.map { it.value }.sum()
    }

    companion object {
        val DEFAULT_MASK = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX"
    }

    private fun String.maskWith(mask: String): Long {
        return this.toBinary().zip(mask).map { (value, maskItem) ->
            when(maskItem) {
                'X' -> value
                else -> maskItem
            }
        }.joinToString("").toLong(2)
    }

    private fun String.getMemoryAdresses(mask: String):List<Long> {
        val memory = this.toBinary().zip(mask).map { (value, maskItem) ->
            when(maskItem) {
                '1' -> maskItem
                'X' -> maskItem
                else -> value
            }
        }.joinToString("")
        var memories = listOf("")
        for(i in memory) {
            memories = when (i) {
                'X' -> {
                    memories.map { listOf(it+"0", it +"1")}.flatMap { it }.toList()
                }
                else -> memories.map {it +i}.toList()
            }
        }
        return memories.map { it.toLong(2) }
    }

    private fun String.toBinary(): String =
            this.toLong().toString(2).padStart(36, '0')

}

