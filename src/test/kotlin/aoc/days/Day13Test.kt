package aoc.days

import aoc.utils.InputReader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class Day13Test {

    val inputs = """
        939
        7,13,x,x,59,x,31,19
    """.trimIndent().split("\n")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // When
            val answer = Day13(inputs).solvePart1()
            println("Test 1: $answer")
            // Then
            assertThat(answer).isEqualTo(259)
        }

        @Test
        fun `Actual answer`() {
            // When
            val answer = Day13(InputReader.readInputAsList(("2020/day13.txt"))).solvePart1()
            println("Part 1: $answer")

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
            val answer = Day13(inputs).solvePart2()
            println("Test 2: $answer")
            // Then
            assertThat(answer)
            assertThat(Day13(listOf("123", "17,x,13,19")).solvePart2()).isEqualTo(3417)
            assertThat(Day13(listOf("123", "67,7,59,61")).solvePart2()).isEqualTo(754018)
            assertThat(Day13(listOf("123", "67,x,7,59,61")).solvePart2()).isEqualTo(779210)
            assertThat(Day13(listOf("123", "67,7,x,59,61")).solvePart2()).isEqualTo(1261476)
            assertThat(Day13(listOf("123", "1789,37,47,1889")).solvePart2()).isEqualTo(1202161486)
        }

        @Test
        fun `Actual answer`() {
            // When
            val answer = Day13(InputReader.readInputAsList(("2020/day13.txt"))).solvePart2()
            println("Part 2: $answer")

            // Then
            assertThat(answer)
        }
    }
}