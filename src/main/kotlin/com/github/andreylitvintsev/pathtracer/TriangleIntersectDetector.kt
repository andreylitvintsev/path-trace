package com.github.andreylitvintsev.pathtracer


class TriangleIntersectDetector(private val camera: Camera) {

    fun isIntersectionExist(viewportX: Int, viewportY: Int, triangle: Triangle): Boolean {
        if (!(camera.viewportLeft..camera.viewportRight).contains(viewportX))
            throw IllegalArgumentException("Illegal 'x' with value '$viewportX'")
        if (!(camera.viewportTop..camera.viewportBottom).contains(viewportY))
            throw IllegalArgumentException("Illegal 'y' with value '$viewportY'")

        TODO()
    }

}
