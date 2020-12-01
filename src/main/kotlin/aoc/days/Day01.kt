package aoc.days

class Day01(val input: List<String>) {

    fun solvePart1(): Int {
        val setOfNumbers = createNumberSet()
        val res = setOfNumbers.find { it.first + it.second == 2020 }
        return res!!.first * res.second
    }

    fun solvePart2(): Int {
        val numbers = input.map { it.toInt() }
        for (i in numbers.indices) {
            for (j in i until numbers.size) {
                for(k in i until numbers.size) {
                    if ((numbers[i]+numbers[j]+numbers[k]) == 2020) return numbers[i]*numbers[j]*numbers[k]
                }
            }
        }
        return 0
    }

    private fun createNumberSet(): MutableSet<Pair<Int, Int>> {
        val numbers = input.map { it.toInt() }
        val setOfNumbers = mutableSetOf<Pair<Int, Int>>()
        for (i in numbers.indices) {
            for (j in i until numbers.size) {
                setOfNumbers.add(Pair(numbers[i], numbers[j]))
            }
        }
        return setOfNumbers
    }
}