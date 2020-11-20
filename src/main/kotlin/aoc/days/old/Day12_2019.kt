package aoc.days.old

import aoc.utils.Vec3
import org.apache.commons.math3.util.ArithmeticUtils.lcm

class Day12_2019(val input: List<String>) {

    val pattern = """-?\d+""".toRegex()

    val moons = mutableListOf<Moon>()

    fun solvePart1(steps: Int, debugStep: Int = 0): Int {
        createUniverse()

        printMoons(true, -1)

        for(i in 0 until steps) {
            step()
            printMoons(debugStep > 0 && (i+1)%debugStep == 0, i)
        }

        var energy: Int = 0

        for(m in moons) {
            energy += m.energy()
        }
        println("Energy: $energy")
        return energy
    }

    fun solvePart2():Long {
        createUniverse()
        val startingX: List<Pair<Int, Int>> = moons.map { it.pos.x to it.vel.x}
        val startingY: List<Pair<Int, Int>> = moons.map { it.pos.y to it.vel.y}
        val startingZ: List<Pair<Int, Int>> = moons.map { it.pos.z to it.vel.z}
        var foundX: Long? = null
        var foundY: Long? = null
        var foundZ: Long? = null
        var stepCount: Long = 0
        do {
            stepCount += 1
            step()
            foundX = if ( foundX == null && startingX == moons.map { it.pos.x to it.vel.x }) stepCount else foundX
            foundY = if ( foundY == null && startingY == moons.map { it.pos.y to it.vel.y }) stepCount else foundY
            foundZ = if ( foundZ == null && startingZ == moons.map { it.pos.z to it.vel.z }) stepCount else foundZ
        } while(foundX == null || foundY == null || foundZ == null)
        val result = lcm(foundX, lcm(foundY, foundZ))
        println("Back to start after $result steps")
        return result
    }

    private fun createUniverse() {
        moons.clear()

        for (line in input) {
            val matches = pattern.findAll(line).iterator()
            val pos = mutableListOf<Int>()
            while (matches.hasNext()) {
                pos.add(matches.next().groupValues[0].toInt())
            }
            moons.add(Moon(Vec3(pos)))
        }
    }

    private fun step() {
        for (m in moons) {
            for (m2 in moons) {
                m.applyOtherMoon(m2)
            }
        }
        for (m in moons) {
            m.updatePosition()
        }
    }

    private fun printMoons(print: Boolean, i: Int) {
        if(print) {
            println("Step: ${i + 1}")
            for (m in moons) {
                println(m)
            }
        }
    }
}

class Moon(var pos: Vec3) {
    var vel: Vec3 = Vec3(0,0,0)

    fun applyOtherMoon(other: Moon) {
        vel += (other.pos - pos).sign()
    }

    override fun toString(): String {
        return "pos=<$pos> \t vel=<$vel>"
    }

    fun updatePosition() {
        pos += vel
    }

    fun energy(): Int = pos.abs().sum() * vel.abs().sum()
}