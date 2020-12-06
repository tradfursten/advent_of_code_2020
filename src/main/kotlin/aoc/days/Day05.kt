package aoc.days

class Day05 {
    private var seats: List<Int>
    val inputs: List<String>

    constructor(inputs: List<String>) {
        this.inputs = inputs
        this.seats = inputs.map { line ->
            var rStart = 0
            var rEnd = 127
            var cStart = 0
            var cEnd = 8
            for (i in 0..6) {
                when(line[i]) {
                    'F' -> {
                        rEnd = rStart + (rEnd - rStart)/2
                    }
                    'B' -> {
                        rStart = rEnd - (rEnd - rStart)/2
                    }
                }
            }
            for(i in 7..9) {
                when(line[i]) {
                    'L' -> {
                        cEnd = cStart + (cEnd - cStart)/2
                    }
                    'R' -> {
                        cStart = cEnd - (cEnd - cStart)/2
                    }
                }
            }
            rStart * 8 + cStart
        }
    }

    fun solvePart1():Int {
        return seats.maxOf { it }
    }

    fun solvePart2():Int {
        val sortedSeats = seats.sorted()
        for ( i in 1 until sortedSeats.size) {
            val current = sortedSeats[i]
            if (current - sortedSeats[i-1] != 1) {
                return current -1
            }
        }
        throw IllegalThreadStateException("Seat not found")
    }
}