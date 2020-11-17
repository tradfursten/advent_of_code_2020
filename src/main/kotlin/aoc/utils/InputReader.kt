package aoc.utils

import java.io.File
import java.net.URI

object InputReader {

    fun readInputAsString(fileName: String, delimiter: String = ""): String =
            readInputAsList(fileName).reduce { a, b -> "$a$delimiter$b" }

    fun readInputAsList(fileName: String): List<String> =
            File(fileName.toURI()).readLines()

    private fun String.toURI(): URI =
            InputReader.javaClass.classLoader.getResource(this)?.toURI() ?: throw IllegalArgumentException("File not found: $this")
}