package com.github.andreylitvintsev.pathtracer


data class RayWithTriangleIntersection(val t: Float, val u: Float, val v: Float)

class TriangleIntersectDetector(private val camera: Camera) {

    fun isIntersectionExist(viewportX: Int, viewportY: Int, triangle: Triangle): Boolean {
        if (!(camera.viewportLeft..camera.viewportRight).contains(viewportX))
            throw IllegalArgumentException("Illegal 'x' with value '$viewportX'")
        if (!(camera.viewportTop..camera.viewportBottom).contains(viewportY))
            throw IllegalArgumentException("Illegal 'y' with value '$viewportY'")

        TODO()
    }

    fun findIntersection(viewportX: Float, viewportY: Float, triangle: Triangle): RayWithTriangleIntersection? {
        val dir = Vector(camera.position, Point(viewportX, viewportY, camera.focalLength)).normalize()

        val edge1 = Vector(triangle.point1, triangle.point2)
        val edge2 = Vector(triangle.point1, triangle.point3)

        val pvec = dir.cross(edge2)
        val det = edge1.dot(pvec)

        if (det == 0f) return null

        val invDet = 1.0f / det
        val tvec = Vector(triangle.point1, camera.position)

        val u = tvec.dot(pvec) * invDet
        if (u < 0f || u > 1f) return null

        val qvec = tvec.cross(edge1)

        val v = dir.dot(qvec) * invDet
        if (v < 0f || u + v > 1f) return null

        val t = edge2.dot(qvec) * invDet

        return RayWithTriangleIntersection(t, u, v)
    }

}
