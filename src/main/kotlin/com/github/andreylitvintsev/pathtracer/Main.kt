package com.github.andreylitvintsev.pathtracer

import java.awt.Color
import java.awt.image.BufferedImage
import java.io.File
import java.util.*
import javax.imageio.ImageIO

fun main(args: Array<String>) {
    val image = BufferedImage(300, 300, BufferedImage.TYPE_INT_RGB)

    val geometry = parseGeometry()
    // TODO: поворот камеры

    val camera = Camera(Point(0f, 0f, -20f), 0.98f * 25.4f, 0.98f * 25.4f/*0.735f * 25.4f*/, 35f)

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

            if (getAllIntersections(geometry, triangleIntersectDetector, x, y).isNotEmpty()) {
                image.setRGB(xIndex, yIndex, Color.WHITE.rgb)
            }
        }
    }

    ImageIO.write(image, "png", File("./result.png"))
}

fun getAllIntersections(geometry: Geometry, triangleIntersectDetector: TriangleIntersectDetector, x: Float, y: Float): List<Triangle> {
    val intersectionTriangles = mutableListOf<Triangle>()
    repeat(geometry.facesCount) { faceIndex ->
        val triangle = geometry.getTriangleByIndex(faceIndex)
        val intersection = triangleIntersectDetector.findIntersection(x, y, triangle)
        if (intersection != null) {
            intersectionTriangles.add(triangle)
        }
    }
    return intersectionTriangles
}

fun parseGeometry(): Geometry {
    val scanner = Scanner(File("untitled.ply").inputStream())

    var vertexCount = -1
    var faceCount = -1
    var currentLine = scanner.nextLine()
    while (currentLine != "end_header") {
        when {
            currentLine.startsWith("element vertex") -> vertexCount = currentLine.split(" ").last().toInt()
            currentLine.startsWith("element face") -> faceCount = currentLine.split(" ").last().toInt()
        }
        currentLine = scanner.nextLine()
    }
    require(vertexCount != -1 && faceCount != -1)

    val points = mutableListOf<Point>()
    repeat(vertexCount) {
        scanner.nextLine().split(" ").let {
            points.add(Point(it[0].toFloat(), it[1].toFloat(), it[2].toFloat()))
        }
    }

    val faceDescriptors = mutableListOf<Geometry.FaceDescriptor>()
    repeat(faceCount) {
        scanner.nextLine().split(" ").let {
            faceDescriptors.add(Geometry.FaceDescriptor(intArrayOf(it[1].toInt(), it[2].toInt(), it[3].toInt())))
        }
    }

    return Geometry(points, faceDescriptors)
}
