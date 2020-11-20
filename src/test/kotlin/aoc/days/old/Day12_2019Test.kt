package aoc.days.old

import aoc.utils.InputReader
import aoc.utils.Vec3
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class Day12_2019Test {

    val inputs = """
        <x=-1, y=0, z=2>
        <x=2, y=-10, z=-7>
        <x=4, y=-8, z=8>
        <x=3, y=5, z=-1>
    """.trimIndent().split("\n")

    val inputs2 = """<x=-8, y=-10, z=0>
<x=5, y=5, z=10>
<x=2, y=-7, z=3>
<x=9, y=-8, z=-3>""".trimIndent().split("\n")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example velocity for steps`() {
            // When
            val day12 = Day12_2019(inputs)
            day12.solvePart1(0)

            // Then
            assertThat(day12.moons.map { m -> m.vel }).containsOnly(Vec3(0,0,0))

            val answer = day12.solvePart1(10, 1)
            assertThat(answer).isEqualTo(179)

            val answer2 = Day12_2019(inputs2).solvePart1(100, 10)
            assertThat(answer2).isEqualTo(1940)
        }

        @Test
        fun `Actual answer`() {
            // When
            val answer = Day12_2019(InputReader.readInputAsList(("2019/day_12_2019.txt"))).solvePart1(1000, 100)

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
            val answer = Day12_2019(inputs).solvePart2()

            // Then
            assertThat(answer).isEqualTo(2772)


            val answer2 = Day12_2019(inputs2).solvePart2()

            // Then
            assertThat(answer2).isEqualTo(4686774924)
        }

        @Test
        fun `Actual answer`() {
            // When
            val answer = Day12_2019(InputReader.readInputAsList(("2019/day_12_2019.txt"))).solvePart2()

            // Then
            assertThat(answer)
        }
    }
}