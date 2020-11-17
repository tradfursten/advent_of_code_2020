package aoc.utils

import aoc.utils.InputReader.readInputAsList
import aoc.utils.InputReader.readInputAsString
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test


class InputReaderTest {

    @Nested
    inner class ReadInputAsString {
        @Test
        fun `concatenates lines without delimiter` () {
            val input = readInputAsString("test_read_file.txt")
            assertThat(input).isEqualTo("1234")
        }
        @Test
        fun `concatenates lines with delimiter` () {
            val input = readInputAsString("test_read_file.txt", "|")
            assertThat(input).isEqualTo("1|2|3|4")
        }
        @Test
        fun `throws exception if file is not found`() {
            assertThatThrownBy {
                readInputAsString("not_found")
            }.isInstanceOf(IllegalArgumentException::class.java)
        }
    }

    @Nested
    inner class ReadInputAsList {

        @Test
        fun `read lines`() {
            val input = readInputAsList("test_read_file.txt")
            assertThat(input).containsExactly("1", "2", "3", "4")
        }
        @Test
        fun `throws exception if file is not found`() {
            assertThatThrownBy {
                readInputAsList("not_found")
            }.isInstanceOf(IllegalArgumentException::class.java)
        }
    }

}