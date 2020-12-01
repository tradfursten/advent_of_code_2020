package aoc.days.old

import aoc.utils.InputReader
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class Day15_2019Test {

    val inputs = "1,9,10,3,2,3,11,0,99,30,40,50"

    @Nested
    @DisplayName("Part 1")
    inner class Part1 {
        @Test
        fun `Matches example`() {
            // When
            //val answer = Day15_2019("1,9,10,3,2,3,11,0,99,30,40,50").solvePart1()
           /* assertThat(answer.program[0]).isEqualTo(3500)
            assertThat(Day15_2019("1,0,0,0,99").solvePart1().program[0]).isEqualTo(2)
            assertThat(Day15_2019("2,3,0,3,99").solvePart1().program[0]).isEqualTo(2)
            assertThat(Day15_2019("2,4,4,5,99,0").solvePart1().program[0]).isEqualTo(2)
            assertThat(Day15_2019("1,1,1,4,99,5,6,0,99").solvePart1().program[0]).isEqualTo(30)
            assertThat(Day15_2019(InputReader.readInputAsString("2019/day_2_2019_part1.intcode")).solvePart1().program[0]).isEqualTo(4023471)
        */    runBlocking {
              //  assertThat(runWithInput(1, InputReader.readInputAsString("2019/day_5_2019.intcode"))).isEqualTo(5044655)
              //  assertThat(runWithInput(5, InputReader.readInputAsString("2019/day_5_2019.intcode"))).isEqualTo(7408802)
                assertThat(runWithoutInput("109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99"))
                assertThat(runWithInput(1, InputReader.readInputAsString("2019/day_9_2019.intcode"))).isEqualTo(2494485073)
                assertThat(runWithInput(2, InputReader.readInputAsString("2019/day_9_2019.intcode"))).isEqualTo(44997)


            }
        }

        @Test
        fun `Actual answer`() {
            // When
            val answer = Day15_2019(InputReader.readInputAsString(("2019/day_15_2019.intcode"))).solvePart1()

            // Then
            assertThat(answer)
        }
    }

    private fun runWithInput(v: Long, input: String): Long {

        val computer = IntCode(input)
        var o: Long = 0L
        runBlocking {
            computer.input.send(v)
            val computerJob = async {
                computer.run()
            }
            while(!computer.output.isClosedForReceive){
                o = computer.output.receive()
                println("Output: $o")

            }
            computerJob.await()
            //o = computer.output.receive()
        }
        //println(computer.program)
        return o
    }

    private fun runWithoutInput(input: String): Long {

        val computer = IntCode(input)
        var o = 0L
        runBlocking {
            val computerJob = async {
                computer.run()
            }
            while(!computer.output.isClosedForReceive){
                println("Output: ${computer.output.receive()}")

            }
            computerJob.await()
        }
        //println(computer.program)
        return o
    }

    @Nested
    @DisplayName("Part 2")
    inner class Part2 {
        @Test
        fun `Matches example`() {
            // When
            val answer = Day15_2019(inputs).solvePart2()

            // Then
            assertThat(answer)
        }

        @Test
        fun `Actual answer`() {
            // When
            val answer = Day15_2019(InputReader.readInputAsString(("2019/day_15_2019.intcode"))).solvePart2()

            // Then
            assertThat(answer)
        }
    }
}