package aoc.days

import aoc.utils.InputReader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class Day11Test {

    val inputs = """
        L.LL.LL.LL
        LLLLLLL.LL
        L.L.L..L..
        LLLL.LL.LL
        L.LL.LL.LL
        L.LLLLL.LL
        ..L.L.....
        LLLLLLLLLL
        L.LLLLLL.L
        L.LLLLL.LL
    """.trimIndent().split("\n")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // When
            val answer = Day11(inputs).solvePart1()
            println("Test 1: $answer")
            // Then
            assertThat(answer).isEqualTo(37)
        }

        @Test
        fun `Actual answer`() {
            // When
            val answer = Day11(InputReader.readInputAsList(("2020/day11.txt"))).solvePart1()
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
            val answer = Day11(inputs).solvePart2()
            println("Test 2: $answer")

            // Then
            assertThat(answer)
        }

        @Test
        fun `Actual answer`() {
            // When
            val answer = Day11(InputReader.readInputAsList(("2020/day11.txt"))).solvePart2()
            println("Part 2: $answer")

            // Then
            assertThat(answer)
        }
    }
}