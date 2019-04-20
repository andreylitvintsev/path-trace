package com.github.andreylitvintsev.pathtracer

import java.lang.Math.sqrt


data class Vector(
    val x: Float, val y: Float, val z: Float
) {

    constructor(a: Point, b: Point) : this(b.x - a.x, b.y - a.y, b.z - a.z)

    fun dot(vector: Vector): Float {
        return this.x * vector.x + this.y * vector.y + this.z * vector.z
    }

    fun cross(vector: Vector): Vector {
        val resultX = this.y * vector.z - this.z * vector.y
        val resultY = this.z * vector.x - this.x * vector.z
        val resultZ = this.x * vector.y - this.y * vector.x
        return Vector(resultX, resultY, resultZ)
    }

    fun length(): Float {
        return sqrt((x * x + y * y + z * z).toDouble()).toFloat()
    }

    fun multiply(value: Float): Vector {
        return Vector(x * value, y * value, z * value)
    }

    fun normalize(): Vector {
        val vectorLength = length()
        val resultX = this.x / vectorLength
        val resultY = this.y / vectorLength
        val resultZ = this.z / vectorLength
        return Vector(resultX, resultY, resultZ)
    }

    fun revert(): Vector {
        val resultX = -this.x
        val resultY = -this.y
        val resultZ = -this.z
        return Vector(resultX, resultY, resultZ)
    }

    fun asPoint(): Point = Point(x, y, z)

}
