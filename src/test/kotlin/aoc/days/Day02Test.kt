package aoc.days

import aoc.utils.InputReader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class Day02Test {

    val inputs =  """
        1-3 a: abcde
        1-3 b: cdefg
        2-9 c: ccccccccc
    """.trimIndent().split("\n")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // When
            val answer = Day02(inputs).solvePart1()
            println("test $answer")

            // Then
            assertThat(answer)
        }

        @Test
        fun `Actual answer`() {
            // When
            val answer = Day02(InputReader.readInputAsList(("2020/day02.txt"))).solvePart1()
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
            val answer = Day02(inputs).solvePart2()
            println("test 2:  $answer")

            // Then
            assertThat(answer)
        }

        @Test
        fun `Actual answer`() {
            // When
            val answer = Day02(InputReader.readInputAsList(("2020/day02.txt"))).solvePart2()
            println("Part 2: $answer")
            // Then
            assertThat(answer)
        }
    }
}