package aoc.days

import java.util.*

class Day18 {
    val inputs: List<String>
    constructor(inputs: List<String>) {
        this.inputs = inputs
    }

    private fun findMatchingParenthesis(s: String): Pair<Int, Int> {
        var stack = Stack<Int>()
        var openBracket = 0
        var pos = 0
        for (c in s) {
            when (c) {
                '(' -> {
                    openBracket++
                    stack.push(pos)
                }
                ')' -> {
                    return stack.pop() to pos
                }

            }
            pos++
        }
        throw IllegalStateException("No matching paranthesis")
    }

    private fun cleanUpAndComputeWithPrecedense(s: String): String {

        var tmpS = s
        val regex = """\(|\)""".toRegex()
        while (tmpS.contains(regex)) {
            val p = findMatchingParenthesis(tmpS)
            tmpS = tmpS.replaceRange(p.first, p.second+1, cleanUpAndComputeWithPrecedense(tmpS.substring(p.first+1, p.second)))
        }

        val parts = tmpS.split(" ")



        val res: Triple<Long, String, ((Long, Long) -> Long)> = parts.fold(Triple(0L,"" ,{ a: Long, b: Long -> b}), { acc, s ->
            when (s) {
                "+" -> Triple(acc.first , acc.second, { a: Long, b: Long -> a + b})
                "*" -> Triple(0L, acc.second+ " " + acc.first + " *", { a: Long, b: Long -> b})
                else -> Triple(acc.third(acc.first, s.toLong()), acc.second,  {a: Long, b:Long -> b})
            }
        })

        val split = (res.second + " " + res.first).trim().split(" ")
        val r2: Pair<Long, ((Long, Long) -> Long)>  = split
                .fold(0L to {a: Long, b: Long -> b}, { acc, s ->
                when (s) {
                    "+" -> acc.first to { a: Long, b: Long -> a + b}
                    "*" -> acc.first to { a: Long, b: Long -> a * b}
                    else -> acc.second(acc.first, s.toLong()) to {a: Long, b:Long -> b}
                }
            })
        return r2.first.toString()
    }

    private fun cleanUpAndCompute(s: String): String {
        var tmpS = s
        val regex = """\(|\)""".toRegex()
        while (tmpS.contains(regex)) {
            val p = findMatchingParenthesis(tmpS)
            tmpS = tmpS.replaceRange(p.first, p.second+1, cleanUpAndCompute(tmpS.substring(p.first+1, p.second)))
        }

        val parts = tmpS.split(" ")


        val res: Pair<Long, ((Long, Long) -> Long)> = parts.fold(0L to {a: Long, b: Long -> b}, { acc, s ->
           when (s) {
               "+" -> acc.first to { a: Long, b: Long -> a + b}
               "*" -> acc.first to { a: Long, b: Long -> a * b}
               else -> acc.second(acc.first, s.toLong()) to {a: Long, b:Long -> b}
           }
        })

        return res.first.toString()
    }

    fun solvePart1():Long {
        return inputs.map { cleanUpAndCompute(it).toLong() }.sum()
    }

    fun solvePart2():Long {
        return inputs.map {
            cleanUpAndComputeWithPrecedense(it).toLong()
        }.sum()
    }


}