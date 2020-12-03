package aoc.days

class Day02(val input: List<String>) {

    fun solvePart1(): Int {
        val k = input.map { l ->
            parseLine(l)
        }
        val p = k.filter { pw ->
                    val f = pw.third.filter { it == pw.second }.length
                    f >= pw.first.first && f <= pw.first.last
                }
        return p.size
    }

    private fun parseLine(line: String): Triple<IntRange, Char, String> {
        val parts = line.split(" ")
        val range = parts[0].split("-")
        return Triple(IntRange(range[0].toInt(), range[1].toInt()), parts[1][0], parts[2])
    }

    fun solvePart2(): Int {
        return input.map{parseLine(it)}
                .filter{ pw ->
                    (pw.third[pw.first.first-1] == pw.second).xor(pw.third[pw.first.last-1] == pw.second)
                }.size
    }
}