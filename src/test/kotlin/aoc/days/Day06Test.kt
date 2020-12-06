package aoc.days

import aoc.utils.InputReader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class Day06Test {

    val inputs = """
        abc

        a
        b
        c

        ab
        ac

        a
        a
        a
        a

        b
    """.trimIndent().split("\n")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // When
            val answer = Day06(inputs).solvePart1()
            println("Test 1: $answer")
            // Then
            assertThat(answer).isEqualTo(11)
        }

        @Test
        fun `Actual answer`() {
            // When
            val answer = Day06(InputReader.readInputAsList(("2020/day06.txt"))).solvePart1()
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
            val answer = Day06(inputs).solvePart2()
            println("Test 2: $answer")

            // Then
            assertThat(answer).isEqualTo(6)
        }

        @Test
        fun `Actual answer`() {
            // When
            val answer = Day06(InputReader.readInputAsList(("2020/day06.txt"))).solvePart2()
            println("Part 2: $answer")

            // Then
            assertThat(answer)
        }
    }
}