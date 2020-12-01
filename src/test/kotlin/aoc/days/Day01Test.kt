package aoc.days

import aoc.days.Day01
import aoc.utils.InputReader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class Day01Test {

    val inputs = """
        1721
        979
        366
        299
        675
        1456
    """.trimIndent().split("\n")
    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // When
            val answer = Day01(inputs).solvePart1()

            // Then
            assertThat(answer).isEqualTo(514579)
        }

        @Test
        fun `Actual answer`() {
            // When
            val answer = Day01(InputReader.readInputAsList(("2020/day01.txt"))).solvePart1()
            println("part 1: $answer")
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
            val answer = Day01(inputs).solvePart2()

            // Then
            assertThat(answer).isEqualTo(241861950)
        }

        @Test
        fun `Actual answer`() {
            // When
            val answer = Day01(InputReader.readInputAsList(("2020/day01.txt"))).solvePart2()
            println("part 2: $answer")

            // Then
            assertThat(answer)
        }
    }
}