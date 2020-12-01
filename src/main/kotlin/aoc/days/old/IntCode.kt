package aoc.days.old

import aoc.days.old.IntCodeStatus.END
import aoc.days.old.IntCodeStatus.RUN
import kotlinx.coroutines.channels.Channel
import kotlin.math.pow


class IntCode {
    var program: MutableMap<Long, Long>
    val input: Channel<Long> = Channel(Channel.UNLIMITED)
    val output: Channel<Long> = Channel(Channel.CONFLATED)
    var p = 0L
    var status = RUN
    var relativeBase = 0L

    constructor(input: String) {
        val pattern = """-?\d+""".toRegex()
        program = pattern.findAll(input)
                .withIndex()
                .associateTo(mutableMapOf()) { it.index.toLong() to it.value.value.toLong() }
    }

    private fun A(): Long = readValue(1)
    private fun B(): Long = readValue(2)
    private fun C(): Long = readValue(3)
    private fun A(v: Long) = writeValue(1, v)
    private fun C(v: Long) = writeValue(3, v)

    private fun readValue(param: Long): Long = program.getOrDefault(getAccessPlace(param), 0L)

    private fun writeValue(param: Long, value: Long) = program.put(getAccessPlace(param), value)


    private fun getAccessPlace(param: Long): Long {
        return when ((program.getOrDefault(p, 0) / (10.0.pow(param + 1.0)) % 10).toLong()) {
            0L -> program.getOrDefault(p + param, 0L)
            1L -> p + param
            2L -> relativeBase + program.getOrDefault(p + param, 0L)
            else -> throw IllegalStateException("Invalid mode")
        }
    }



    private suspend fun exec() {
        //print("$p -> ${program[p]}")
        when (val opid = (program.getValue(p) % 100).toInt()) {
            // ADD
            1 -> {
                //println(" ${program[p + 1]} (${A()}) + ${program[p + 2]} (${B()}) = ${program[p + 3]}")
                C(A() + B())
                p += 4
            }
            // MUL
            2 -> {
                //println(" ${program[p + 1]} * ${program[p + 2]} = ${program[p + 3]}")
                C(A() * B())
                p += 4
            }
            3 -> {
                val v = input.receive()
                //println(" ${program[p + 1]} <- $v")
                A(v)
                p += 2
            }
            4 -> {
                val element = A()
               // println(" ${program[p + 1]} output $element")
                output.send(element)
                p += 2
            }
            5 -> {
                //println(" ${program[p + 1]}  ${program[p + 2]} ")
                //println("not true ${A()} goto ${B()}")
                p = if (A() != 0L) B() else p + 3
            }
            6 -> {
                //println(" ${program[p + 1]}  ${program[p + 2]} ")
                //println("true ${A()} goto ${B()}")
                p = if (A() == 0L) B() else p + 3
            }
            7 -> {
                //println(" ${program[p + 1]}  ${program[p + 2]} ${program[p + 3]}")
                C(if (A() < B()) 1 else 0)
                p += 4
            }
            8 -> {
               // println(" ${program[p + 1]}  ${program[p + 2]} ${program[p + 3]}")
                C(if (A() == B()) 1 else 0)
                p += 4
            }
            9 -> {
               // println(" ${program[p + 1]} move relative base ${A()}")
                relativeBase += A()
                p += 2
            }
            99 -> {
                status = END
            }
            else -> throw IllegalStateException("Unknown op-code ${opid}")
        }
    }

    suspend fun run() {
        while (status == RUN) {
            exec()
        }

        output.close()
    }
}


enum class IntCodeStatus {
    RUN, END
}