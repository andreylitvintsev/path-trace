package com.github.andreylitvintsev.pathtracer


// TODO: добавить вращение и изменение положения
data class Camera(val position: Point, val viewportWidth: Int, val viewportHeight: Int, val viewportPosition: Float) {

    val viewportLeft: Int = (position.x - viewportWidth / 2f).toInt()
    val viewportTop: Int = (position.y - viewportHeight / 2f).toInt()
    val viewportRight: Int = (position.x + viewportWidth / 2f).toInt()
    val viewportBottom: Int = (position.y + viewportHeight / 2f).toInt()

}
