package aoc.utils

import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

data class Vec2 (val x: Int, val y: Int) {


    companion object {
        val directionsDiagonal = listOf(up(), upRight(), right(), downRight(), down(), downLeft(), left(), upLeft())
        fun up(): Vec2 = Vec2(0, -1)
        fun upRight(): Vec2 = Vec2(1, -1)
        fun upLeft(): Vec2 = Vec2(-1, -1)
        fun down(): Vec2 = Vec2(0, 1)
        fun downRight(): Vec2 = Vec2(1, 1)
        fun downLeft(): Vec2 = Vec2(-1 , 1)
        fun left(): Vec2 = Vec2(-1, 0)
        fun right(): Vec2 = Vec2(1, 0)

    }

    fun rotateDeg(d: Int): Vec2 {
        val r = d * (Math.PI/180)
        val cos = cos(r);
        val sin = sin(r);
        return Vec2((this.x*cos - this.y*sin).roundToInt(), (this.x*sin + this.y*cos).roundToInt())
    }

    operator fun minus(position: Point2D)= Vec2(this.x - position.x, this.y - position.y)
    operator fun plus(v: Vec2) = Vec2(this.x + v.x, this.y+v.y)
    operator fun times(i: Int) = Vec2(this.x * i, this.y * i)
}
