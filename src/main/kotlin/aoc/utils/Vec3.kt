package aoc.utils

import kotlin.math.absoluteValue
import kotlin.math.sign

class Vec3(val x: Int, val y: Int, val z: Int) {

    constructor(v: List<Int>) : this(v[0], v[1], v[2])

    operator fun minus(other: Vec3) = Vec3(x - other.x, y - other.y, z - other.z)
    operator fun plus(other: Vec3) = Vec3(x + other.x, y + other.y, z + other.z)
    fun sign() = Vec3(x.sign, y.sign, z.sign)

    override fun toString(): String {
        return "x=$x, y=$y, z=$z"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Vec3

        if (x != other.x) return false
        if (y != other.y) return false
        if (z != other.z) return false

        return true
    }

    override fun hashCode(): Int {
        var result = x
        result = 31 * result + y
        result = 31 * result + z
        return result
    }

    fun abs(): Vec3 = Vec3(x.absoluteValue, y.absoluteValue, z.absoluteValue)
    fun sum(): Int = x + y + z


}