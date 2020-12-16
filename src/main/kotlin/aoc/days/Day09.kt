package aoc.days

import java.lang.IllegalStateException

class Day09(val inputs: List<String>) {
    private val xmas: List<Long> = inputs.map { it.toLong() }.toList()

    fun solvePart1(preamble: Int):Long {
        for(i in preamble until xmas.size) {
            if (!isPartOfXMAS(i, preamble)) {
                return xmas[i]
            }
        }
        throw IllegalStateException("Everything is part of XMAS")
    }

    fun solvePart2(preamble: Int):Long {
        val wrong = solvePart1(preamble)
        var i = 0
        var j = 0
        var tmpSum = 0L
        while(xmas[i] != wrong) {
            tmpSum = 0L
            j = i
            while(tmpSum<wrong) {
                tmpSum += xmas[j]
                j++
            }
            if(tmpSum == wrong) {
                val smallest = xmas.subList(i, j).minOrNull() ?: 0
                val largest = xmas.subList(i, j).maxOrNull() ?: 0
                return smallest + largest
            }
            i++
        }
        return 0
    }

    private fun isPartOfXMAS(i: Int, preamble: Int): Boolean {
        val current = xmas[i]
        var tmp = 0L
        for (j in 1 .. preamble) {
            tmp = current - xmas[i-j]
            if (tmp in xmas.subList(i-preamble, i-1)) {
                return true
            }
        }
        return false
    }
}