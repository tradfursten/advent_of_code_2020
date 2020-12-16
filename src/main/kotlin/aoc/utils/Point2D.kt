package aoc.utils

data class Point2D(val x: Int, val y: Int) {
    override fun toString(): String {
        return "(x=$x, y=$y)"
    }

    fun up(): Point2D = copy(y = y+1)
    fun upRight(): Point2D = copy(y = y+1, x= x+1)
    fun upLeft(): Point2D = copy(y = y+1, x = x-1)
    fun down(): Point2D = copy(y = y-1)
    fun downRight(): Point2D = copy(y = y-1, x = x+1)
    fun downLeft(): Point2D = copy(y = y-1, x = x-1)
    fun left(): Point2D = copy(x = x-1)
    fun right(): Point2D = copy(x = x+1)

    fun neighbors():List<Point2D> = listOf(up(), right(), down(), left())
    fun neighborsDiagonal():List<Point2D> = listOf(up(), upRight(), right(), downRight(), down(), downLeft(), left(), upLeft())

    companion object {
        val ORIGIN = Point2D(0, 0)
    }

    operator fun plus(v: Vec2) = Point2D(this.x + v.x, this.y+v.y)

}

fun List<String>.getOrNull(p: Point2D): Char? =
       this.getOrNull(p.y)?.getOrNull(p.x)
