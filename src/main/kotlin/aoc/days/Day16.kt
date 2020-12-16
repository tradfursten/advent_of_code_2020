package aoc.days

class Day16 {
    val inputs: List<String>

    val fields = mutableMapOf<String, List<IntRange>>()

    lateinit var myTicket: List<Int>

    val neighbourTickets = mutableListOf<List<Int>>()

    constructor(inputs: List<String>) {
        this.inputs = inputs
        var parseState = "fields"
        val findRange = """(\d+)-(\d+)""".toRegex()
        for( l in inputs) {
            when (parseState) {
                "fields" -> {
                    if (l != "") {
                        val name = l.substring(0 until l.indexOf(":"))
                        val list = mutableListOf<IntRange>()
                        findRange.findAll(l).iterator().forEach {
                            list.add(it.groupValues[1].toInt()..it.groupValues[2].toInt())
                        }
                        fields[name] = list
                    } else {
                        parseState = "myTicket"
                    }
                }
                "myTicket" -> {
                    if (l != "") {
                        if (l != "your ticket:") {
                            this.myTicket = l.split(",").map { it.toInt() }.toList()
                        }
                    } else {
                        parseState = "neighbourTicket"
                    }
                }
                "neighbourTicket" -> {
                    if (l != "") {
                        if (l != "nearby tickets:") {
                            this.neighbourTickets.add(l.split(",").map { it.toInt() }.toList())
                        }
                    } else {
                        parseState = "neighbourTicket"
                    }
                }
            }
        }

    }

    fun solvePart1():Int {
        val k = neighbourTickets.map { t ->
            t.filterNot { n ->
                fields.any { fieldRanges ->
                    //println("${fieldRanges.key} in $n ${fieldRanges.value.any { n in it}}")
                    fieldRanges.value.any { n in it}
                }
            }
        }.flatten()
        println(k)
        return k.sum()
    }

    fun solvePart2():Long {
        val fieldIndex = mutableMapOf<String, List<Int>>()
        fields.forEach {
            fieldIndex[it.key] = myTicket.indices.toMutableList()
        }
        val k = neighbourTickets.map { t ->
            if (t.all { n ->
                fields.any { fieldRanges ->
                    //println("${fieldRanges.key} in $n ${fieldRanges.value.any { n in it}}")
                    fieldRanges.value.any { n in it}
                }
            }) {
                t
            } else {
                emptyList()
            }
        }.filter { it.isNotEmpty() }

        k.forEach { ticket ->
                fields.forEach { field ->
                    fieldIndex[field.key] = fieldIndex[field.key]?.filter { i ->
                        field.value.any { ticket[i] in it }
                    }?.toMutableList() ?: mutableListOf()
                }
        }
        println(fieldIndex)

        while (fieldIndex.values.any { it.size > 1 }) {
            fieldIndex.filter { it.value.size == 1 }
                    .forEach { (k, v) ->
                        // remove value from all other
                        fieldIndex.filter { it.key != k }
                                .forEach { (k2, v2) ->
                                    fieldIndex[k2] = v2.filter {it != v[0]}
                                }
                    }
        }



        println(fieldIndex)



        return fieldIndex.filter { it.key.startsWith("departure") }
                .map { myTicket[it.value[0]].toLong()}
                .reduce { acc, i -> acc * i }

    }
}