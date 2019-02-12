package com.github.andreylitvintsev.pathtracer

fun main(args: Array<String>) {
//    val image = BufferedImage(300, 300, BufferedImage.TYPE_INT_RGB);
//    for (i in 100..200) {
//        for (j in 100..200) {
//            image.setRGB(i, j, Color.RED.rgb)
//        }
//    }
//    ImageIO.write(image, "png", File("./result.png"))

    val camera = Camera(Point(0f, 0f, -5f), 300, 300, 0f)
    val triangle = Triangle(Point(0f, 5f, 8f), Point(5f, 0f, 8f), Point(0f, 0f, 8f))
    val triangleIntersectDetector = TriangleIntersectDetector(camera)

    println(triangleIntersectDetector.findIntersectionPoint(7, 3, triangle))
}
