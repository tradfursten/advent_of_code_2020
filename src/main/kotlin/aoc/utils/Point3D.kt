package aoc.utils

data class Point3D(val x: Int, val y: Int, val z: Int) {

    fun neighbours(): List<Point3D> {
        val r = mutableListOf<Point3D>()
        for (z_ in z-1..z+1) {
            for (y_ in y-1..y+1) {
                for (x_ in x-1..x+1) {
                    val p = Point3D(x_, y_, z_)
                    if(this != p) {
                        r.add(p)
                    }
                }
            }
        }
        return r
    }
}