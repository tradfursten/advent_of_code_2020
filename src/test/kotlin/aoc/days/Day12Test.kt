package aoc.days

import aoc.utils.InputReader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class Day12Test {

    val inputs = """
        F10
        N3
        F7
        R90
        F11
    """.trimIndent().split("\n")

    val test = """
        F1
        R90
        L90
        N1
        S1
        W1
        E1
    """.trimIndent().split("\n")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // When
            val answer = Day12(inputs).solvePart1()
            println("Test 1: $answer")
            // Then
            assertThat(answer).isEqualTo(25)

            assertThat(Day12(test).solvePart1()).isEqualTo(1)
        }

        @Test
        fun `Actual answer`() {
            // When
            val answer = Day12(InputReader.readInputAsList(("2020/day12.txt"))).solvePart1()
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
            val answer = Day12(inputs).solvePart2()
            println("Test 2: $answer")

            // Then
            assertThat(answer)

            Day12(listOf("L90")).solvePart2()
        }

        @Test
        fun `Actual answer`() {
            // When
            val answer = Day12(InputReader.readInputAsList(("2020/day12.txt"))).solvePart2()
            println("Part 2: $answer")

            // Then
            assertThat(answer)
        }
    }
}