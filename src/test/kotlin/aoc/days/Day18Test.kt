package aoc.days

import aoc.utils.InputReader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class Day18Test {

    val inputs = """
        1 + 2 * 3 + 4 * 5 + 6
        1 + (2 * 3) + (4 * (5 + 6))
        2 * 3 + (4 * 5)
        5 + (8 * 3 + 9 + 3 * 4 * 3)
        5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))
        ((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2
    """.trimIndent().split("\n")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // When
            val answer = Day18(inputs).solvePart1()
            println("Test 1: $answer")
            // Then
            assertThat(answer)
        }

        @Test
        fun `Actual answer`() {
            // When
            val answer = Day18(InputReader.readInputAsList(("2020/day18.txt"))).solvePart1()
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
            val answer = Day18(inputs).solvePart2()
            println("Test 2: $answer")

            // Then
            assertThat(answer)
        }

        @Test
        fun `Actual answer`() {
            // When
            val answer = Day18(InputReader.readInputAsList(("2020/day18.txt"))).solvePart2()
            println("Part 2: $answer")

            // Then
            assertThat(answer)
        }
    }
}