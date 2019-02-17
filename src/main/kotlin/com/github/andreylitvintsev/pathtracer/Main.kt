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
        intArrayOf(0,1, 1)
    )

    val camera = Camera(Point(0f, 0f, 0f), 300, 300, -1f)
    val triangle = Triangle(Point(100f, 100f, -1f), Point(200f, 50f, -1f), Point(100f, 200f, -1f))
    val triangleIntersectDetector = TriangleIntersectDetector(camera)

    for (i in 0..camera.viewportWidth) {
        for (j in 0..camera.viewportHeight) {
            val intersection = triangleIntersectDetector.findIntersection(i, j, triangle)
            if (intersection != null) {
                println(intersection)

                image.setRGB(i, j, Color.GREEN.rgb)
            }
        }
    }

    ImageIO.write(image, "png", File("./result.png"))
}
