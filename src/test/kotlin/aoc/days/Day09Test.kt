package aoc.days

import aoc.utils.InputReader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class Day09Test {

    val inputs = """
        35
        20
        15
        25
        47
        40
        62
        55
        65
        95
        102
        117
        150
        182
        127
        219
        299
        277
        309
        576
    """.trimIndent().split("\n")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // When
            val answer = Day09(inputs).solvePart1(5)
            println("Test 1: $answer")
            // Then
            assertThat(answer).isEqualTo(127L)
        }

        @Test
        fun `Actual answer`() {
            // When
            val answer = Day09(InputReader.readInputAsList(("2020/day09.txt"))).solvePart1(25)
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
            val answer = Day09(inputs).solvePart2(5)
            println("Test 2: $answer")

            // Then
            assertThat(answer)
        }

        @Test
        fun `Actual answer`() {
            // When
            val answer = Day09(InputReader.readInputAsList(("2020/day09.txt"))).solvePart2(25)
            println("Part 2: $answer")

            // Then
            assertThat(answer)
        }
    }
}