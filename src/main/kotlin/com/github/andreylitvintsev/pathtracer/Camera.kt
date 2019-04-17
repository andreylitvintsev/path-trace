package com.github.andreylitvintsev.pathtracer

import java.lang.Math.*


// TODO: разобраться с положением объектов (сзади и спереди)
// TODO: добавить вращение и изменение положения
data class Camera(
    val position: Point,
    val apertureWidth: Float,
    val apertureHeight: Float,
    val focalLength: Float // TODO: уточнить
) {

    companion object {
        // TODO: протестить работоспособность
        fun fromViewAngle(position: Point, apertureWidth: Float, apertureHeight: Float, horizontalViewAngle: Float): Camera {
            return Camera(
                position,
                apertureWidth,
                apertureHeight,
                focalLength = (apertureWidth / 2f) * (1f * tan(toRadians(horizontalViewAngle.toDouble()))).toFloat()
            )
        }
    }

    val viewportLeft: Int = (position.x - apertureWidth / 2f).toInt()
    val viewportTop: Int = (position.y - apertureHeight / 2f).toInt()
    val viewportRight: Int = (position.x + apertureWidth / 2f).toInt()
    val viewportBottom: Int = (position.y + apertureHeight / 2f).toInt()

}
