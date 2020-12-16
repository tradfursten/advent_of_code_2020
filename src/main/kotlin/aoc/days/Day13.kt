package aoc.days

import aoc.utils.gcd

class Day13(inputs: List<String>) {
    private var allBusses: List<String>
    val timestamp: Long
    val busses: List<Long>

    init {
        timestamp = inputs[0].toLong()
        busses = """\d+""".toRegex().findAll(inputs[1])
                .asIterable()
                .map { it.value.toLong() }
                .toList()
        allBusses = inputs[1].split(",")

    }

    fun solvePart1():Long {
        val r = busses.map { it to it - (timestamp % it)}.minByOrNull { it.second } ?: 0L to 0L

        return r.first * r.second
    }

    fun solvePart2():Long {
        var done = false
        var i = 0
        var t = 0L
        var step = 1L
        val bussesInStep = mutableSetOf<Long>()
        while(!done) {
            done = true
            t+=step
            for (i in allBusses.indices) {
               if(allBusses[i] != "x") {
                   val curr = allBusses[i].toLong()
                   if ((t+i) % curr != 0L) {
                       done = false
                       break
                   } else {
                       if (curr !in bussesInStep) {
                           bussesInStep.add(curr)
                           step = (curr * step)/ gcd(curr, step)
                       }

                   }

               }
            }
            i++
            if (i%10 == 0) {
                println("$i: $t step: $step")
            }
        }
        return t
    }
}