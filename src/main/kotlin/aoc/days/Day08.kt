package aoc.days

import java.lang.IllegalStateException

class Day08(val inputs: List<String>) {
    private val program = inputs.map { Instruction(it) }.toList()
    var accumulator = 0
    var pointer = 0


    fun solvePart1():Int {
        while(pointer < program.size && !program[pointer].visited) {
            program[pointer].execute()
        }
        if (pointer >= program.size) println("Terminated successfully")
        return accumulator
    }

    fun solvePart2():Int {
        solvePart1()
        var lastChangedOperation = 0
        while(pointer<program.size) {
            pointer = 0
            accumulator = 0
            var tempProgram = program.map { Instruction(it) }
            lastChangedOperation = findNextUntestedJmpOrNop(lastChangedOperation)
            tempProgram[lastChangedOperation].changeOperation()
            println("Changed instruction @ $lastChangedOperation to ${tempProgram[lastChangedOperation]}")
            while(pointer < program.size && !tempProgram[pointer].visited) {
                tempProgram[pointer].execute()
            }
            if (pointer >= program.size) println("Terminated successfully")
        }
        return accumulator
    }

    private fun findNextUntestedJmpOrNop(lastChangedOperation: Int): Int {
       for (i in (lastChangedOperation+1) until program.size) {
           if (program[i].visited && program[i].operation in listOf("jmp", "nop")) {
               return i
           }
       }
        throw IllegalStateException("Could not find untested but visited operation")
    }

    inner class Instruction {
        fun execute(){
            when(operation) {
                "nop" -> pointer++
                "acc" -> {
                    accumulator += argument
                    pointer++
                }
                "jmp" -> pointer += argument
            }
            visited = true
        }

        var operation: String
        private val argument: Int
        var visited = false

        constructor(code: String) {
            var parts = code.split(" ")
            operation = parts[0]
            argument = parts[1].toInt()
        }

        constructor(code: Instruction) {
            operation = code.operation
            argument = code.argument
        }

        override fun toString(): String {
           return "$operation $argument $visited"
        }

        fun changeOperation() {
            when(operation) {
                "jmp" -> operation = "nop"
                "nop" -> operation = "jmp"
            }
        }
    }
}

