package com.github.andreylitvintsev.pathtracer


data class Point(val x: Float, val y: Float, val z: Float) {

    fun add(point: Point): Point {
        return Point(this.x + point.x, this.y + point.y, this.z + point.z)
    }

}
