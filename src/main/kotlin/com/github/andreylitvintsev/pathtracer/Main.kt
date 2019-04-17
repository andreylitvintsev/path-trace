package com.github.andreylitvintsev.pathtracer

import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

fun main(args: Array<String>) {
    val image = BufferedImage(300, 300, BufferedImage.TYPE_INT_RGB);
//    for (i in 100..200) {
//        for (j in 100..200) {
//            image.setRGB(i, j, Color.RED.rgb)
//        }
//    }
//    ImageIO.write(image, "png", File("./result.png"))

    val colors = arrayOf(
        intArrayOf(1, 0, 0),
        intArrayOf(0, 1, 0),
        intArrayOf(0, 1, 1)
    )

    val camera = Camera(Point(0f, 0f, 0f), 300f, 300f, 1f)

    val triangle = Triangle(Point(0f, 0f, 1f), Point(100f, -50f, 1f), Point(0f, 100f, 1f))
    val triangleIntersectDetector = TriangleIntersectDetector(camera)

    val right = camera.apertureWidth.toDouble() / 2
    val left = -right

    val top = camera.apertureHeight.toDouble() / 2
    val bottom = -top

    val apertureStepWidth: Double = (right - left) / image.width
    val apertureStepHeight: Double = (top - bottom) / image.height

    val halfStepWidth: Double = apertureStepWidth / 2
    val halfStepHeight: Double = apertureStepWidth / 2

    repeat(image.width) { xIndex ->
        repeat(image.height) { yIndex ->
            val x = (xIndex * apertureStepWidth + halfStepWidth + left).toFloat()
            val y = (yIndex * apertureStepHeight + halfStepHeight + bottom).toFloat()

            val intersection = triangleIntersectDetector.findIntersection(x, y, triangle)
            if (intersection != null) {
                image.setRGB(xIndex, yIndex, Color.GREEN.rgb)
            }
        }
    }



//    for (i in -camera.apertureWidth.toInt() / 2..camera.apertureWidth.toInt() / 2) {
//        for (j in -camera.apertureHeight.toInt() / 2..camera.apertureHeight.toInt() / 2) {
//            val intersection = triangleIntersectDetector.findIntersection(i, j, triangle)
//            if (intersection != null) {
////                println(intersection)
//
//                image.setRGB(screenMapX(i, image.width), screenMapY(j, image.height), Color.GREEN.rgb)
//            }
//        }
//    }

    ImageIO.write(image, "png", File("./result.png"))
}

inline fun screenMapX(x: Int, width: Int): Int {
    return width / 2 + x
}

inline fun screenMapY(y: Int, height: Int): Int {
    return height / 2 - y
}
