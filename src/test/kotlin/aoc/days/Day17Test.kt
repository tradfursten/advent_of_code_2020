package aoc.days

import aoc.utils.InputReader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class Day17Test {

    val inputs = """
        .#.
        ..#
        ###
    """.trimIndent().split("\n")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // When
            val answer = Day17(inputs).solvePart1(3)
            println("Test 1: $answer")
            // Then
            assertThat(answer)
        }

        @Test
        fun `Actual answer`() {
            // When
            val answer = Day17(InputReader.readInputAsList(("2020/day17.txt"))).solvePart1(6)
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
            val answer = Day17(inputs).solvePart2(6)
            println("Test 2: $answer")

            // Then
            assertThat(answer)
        }

        @Test
        fun `Actual answer`() {
            // When
            val answer = Day17(InputReader.readInputAsList(("2020/day17.txt"))).solvePart2(6)
            println("Part 2: $answer")

            // Then
            assertThat(answer)
        }
    }
}