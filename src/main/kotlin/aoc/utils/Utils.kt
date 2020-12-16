package aoc.utils

fun gcd(a: Long, b: Long): Long = if (b == 0L) a else gcd(b, a % b)