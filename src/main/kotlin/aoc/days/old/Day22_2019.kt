package aoc.days.old

import java.math.BigInteger

class Day22_2019(val input: List<String>) {

    fun solvePart1() {}

    fun solvePart2(): BigInteger {
        return modulatArithmetic(2020.toBigInteger())
    }


    fun modulatArithmetic(find: BigInteger): BigInteger {
        var memory = arrayOf(BigInteger.ONE, BigInteger.ZERO)
        input.reversed().forEach { instruction ->
            when {
                "stack" in instruction -> {
                    memory[0] = memory[0].negate()
                    memory[1] = memory[1].inc().negate()
                }
                "cut" in instruction -> {
                    memory[1] += instruction.getBigInteger()
                }
                "increment" in instruction -> {
                    instruction.getBigInteger().modPow(NUMBER_OF_CARDS - BigInteger.TWO, NUMBER_OF_CARDS)
                            .also {
                                memory[0] *= it
                                memory[1] *= it
                            }
                }
            }
            memory[0] %= NUMBER_OF_CARDS
            memory[1] %= NUMBER_OF_CARDS
        }

        val power = memory[0].modPow(SHUFFLES, NUMBER_OF_CARDS)
        return ((power * find) + ((memory[1] * (power + NUMBER_OF_CARDS.dec())) * ((memory[0].dec()).modPow(NUMBER_OF_CARDS - BigInteger.TWO, NUMBER_OF_CARDS)))).mod(NUMBER_OF_CARDS)
    }

    companion object {
        val SHUFFLES = 101741582076661.toBigInteger()
        val NUMBER_OF_CARDS = 119315717514047.toBigInteger()
    }

}

private fun String.getBigInteger(): BigInteger = this.split(" ").last().toBigInteger()

