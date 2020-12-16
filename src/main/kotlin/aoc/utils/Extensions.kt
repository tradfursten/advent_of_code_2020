package aoc.utils

import kotlin.math.pow

fun List<String>.toInts(): List<Int> = this.map{it.toInt()}

fun Int.pow(n: Int): Long = this.toDouble().pow(n).toLong()
fun Long.pow(n: Int): Long = this.toDouble().pow(n).toLong()

