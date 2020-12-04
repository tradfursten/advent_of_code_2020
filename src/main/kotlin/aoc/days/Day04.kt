package aoc.days

class Day04 {
    private val passport = mutableListOf<Passport>()

    constructor(input: String) {
        val l = input.split("\n")
        var currentPassport = ""
        for (i in l.indices) {
            if (l[i] == "") {
                passport.add(Passport.create(currentPassport))
                currentPassport = ""
            } else {
                currentPassport += l[i] + " "
            }
        }
        if (currentPassport != "") {
            passport.add(Passport.create(currentPassport))
        }
    }

    fun solvePart1():Int {
        return passport.filter {
            it.hasAllRequiredFields()
        }.size
    }

    fun solvePart2():Int {
        return passport.filter {
            it.hasAllRequiredFields() && it.isValid()
        }.size
    }
}

class Passport(private val byr: String?,
               private val iyr: String?,
               private val eyr: String?,
               private val hgt: String?,
               private val hcl: String?,
               private val ecl: String?,
               private val pid: String?,
               private val cid: String?){


    fun hasAllRequiredFields(): Boolean {
        return byr != null && iyr != null && eyr != null && hgt != null && hcl != null && ecl != null && pid != null
    }

    fun isValid(): Boolean {
        return when {
            byr?.toInt() !in 1920..2002 -> {
                println("Wrong byr: $byr")
                false
            }
            iyr?.toInt() !in 2010..2020 -> {
                println("Wrong iyr: $iyr")
                false
            }
            eyr?.toInt() !in 2020..2030 -> {
                println("Wrong eyr: $eyr")
                false
            }
            !validHeight() -> {
                println("Wrong hgt: $hgt")
                false
            }
            hcl?.matches("#[0-9a-fA-F]{6}".toRegex()) != true -> {
                println("Wrong hcl: $hcl")
                false
            }
            ecl !in "amb blu brn gry grn hzl oth".split(" ") -> {
                println("Wrong ecl: $ecl")
                false
            }
            pid?.matches("[0-9]{9}".toRegex()) != true -> {
                println("Wrong pid: $pid")
                false
            }
            else -> true
        }

    }

    private fun validHeight(): Boolean {
        val height = hgt?.subSequence(0, hgt.length-2).toString()
        val unit = hgt?.subSequence(hgt.length-2, hgt.length)
        return when(unit) {
            "in" -> height.toInt() in 59..76
            "cm" -> height.toInt() in 150..193
            else -> false
        }
    }



    override fun toString(): String {
        return "Passport(byr=$byr, iyr=$iyr, eyr=$eyr, hgt=$hgt, hcl=$hcl, ecl=$ecl, pid=$pid, cid=$cid)"
    }


    companion object {
        fun create(line: String): Passport{
            val parts = line.trim().split(" ")
                    .map { it.split(":") }
                    .map { it[0] to it[1] }
                    .toMap()
            return Passport(parts["byr"], parts["iyr"], parts["eyr"], parts["hgt"], parts["hcl"], parts["ecl"], parts["pid"], parts["cid"])
        }
    }
}