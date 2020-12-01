package aoc.days.old

import aoc.utils.InputReader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class Day22_2019Test {

    val inputs = listOf("cut 3") //("deal into new stack")


    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // When
            val day222019 = Day22_2019(inputs)
            val r = IntRange(0, 10)
                    .map { it.toBigInteger() }
                    .map { it to day222019.modulatArithmetic(it) }
                    //.sortedBy { it.first }
                    .map { it.second }
                    .toList()
            val answer = day222019.modulatArithmetic(0.toBigInteger())

            println(r)
            // Then
            //assertThat(answer).isEqualTo(9)
        }

        @Test
        fun `Actual answer`() {
            // When
            val answer = Day22_2019(InputReader.readInputAsList(("day_22_2019.txt"))).solvePart1()

            // Then
            assertThat(answer)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            // When
            val answer = Day22_2019(inputs).solvePart2()

            // Then
            assertThat(answer)
        }

        @Test
        fun `Actual answer`() {
            // When
            val answer = Day22_2019(InputReader.readInputAsList(("2019/day_22_2019.txt"))).solvePart2()
            println(answer)

            // Then
            assertThat(answer)
        }
    }
}