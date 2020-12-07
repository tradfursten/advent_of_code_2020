package aoc.days

import aoc.utils.InputReader
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class Day07Test {

    val inputs = """
        light red bags contain 1 bright white bag, 2 muted yellow bags.
        dark orange bags contain 3 bright white bags, 4 muted yellow bags.
        bright white bags contain 1 shiny gold bag.
        muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
        shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
        dark olive bags contain 3 faded blue bags, 4 dotted black bags.
        vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
        faded blue bags contain no other bags.
        dotted black bags contain no other bags.
    """.trimIndent().split("\n")

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // When
            val answer = Day07(inputs).solvePart1("shiny gold")
            println("Test 1: $answer")
            // Then
            assertThat(answer).isEqualTo(4)
        }

        @Test
        fun `Actual answer`() {
            // When
            val answer = Day07(InputReader.readInputAsList(("2020/day07.txt"))).solvePart1("shiny gold")
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
            val answer = Day07(inputs).solvePart2("shiny gold")
            println("Test 2: $answer")

            // Then
            assertThat(answer).isEqualTo(32)
        }

        @Test
        fun `Matches example 2`() {
            val input2 = """
                shiny gold bags contain 2 dark red bags.
                dark red bags contain 2 dark orange bags.
                dark orange bags contain 2 dark yellow bags.
                dark yellow bags contain 2 dark green bags.
                dark green bags contain 2 dark blue bags.
                dark blue bags contain 2 dark violet bags.
                dark violet bags contain no other bags.
            """.trimIndent().split("\n")

            val answer2 = Day07(input2).solvePart2("shiny gold")
            println("Test 2 (b): $answer2")
            assertThat(answer2).isEqualTo(126)

        }

        @Test
        fun `Actual answer`() {
            // When
            val answer = Day07(InputReader.readInputAsList(("2020/day07.txt"))).solvePart2("shiny gold")
            println("Part 2: $answer")

            // Then
            assertThat(answer)
        }
    }
}