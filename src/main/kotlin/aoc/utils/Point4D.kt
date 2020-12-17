package aoc.utils

data class Point4D(val x: Int, val y: Int, val z: Int, val w: Int) {

    fun neighbours(): List<Point4D> {
        val r = mutableListOf<Point4D>()
        for(w_ in w-1..w+1) {
            for (z_ in z - 1..z + 1) {
                for (y_ in y - 1..y + 1) {
                    for (x_ in x - 1..x + 1) {
                        val p = Point4D(x_, y_, z_, w_)
                        if (this != p) {
                            r.add(p)
                        }
                    }
                }
            }
        }
        return r
    }
}