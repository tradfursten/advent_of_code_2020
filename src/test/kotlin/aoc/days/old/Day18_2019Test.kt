package aoc.days.old

import aoc.utils.InputReader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class Day18_2019Test {

    val inputs = """#########
#b.A.@.a#
#########""".trimIndent().split("\n")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // When
            val answer = Day18_2019(inputs).solvePart1()

            // Then
            assertThat(answer).isEqualTo(8)
        }

        @Test
        fun `Actual answer`() {
            // When
            val answer = Day18_2019(InputReader.readInputAsList(("2019/day_18_2019.txt"))).solvePart1()

            // Then
            assertThat(answer).isEqualTo(0)
        }
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            // When
            val answer = Day18_2019(inputs).solvePart2()

            // Then
            assertThat(answer)
        }

        @Test
        fun `Actual answer`() {
            // When
            val answer = Day18_2019(InputReader.readInputAsList(("2019/day_18_2019_part2.txt"))).solvePart2()

            // Then
            assertThat(answer).isEqualTo(0)
        }
    }
}