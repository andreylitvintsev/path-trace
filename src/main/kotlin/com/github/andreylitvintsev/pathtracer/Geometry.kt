package com.github.andreylitvintsev.pathtracer

data class Geometry(
    val points: List<Point>,
    val faceDescriptors: List<FaceDescriptor>
) {

    data class FaceDescriptor(
        val pointIndexes: IntArray
    )

    val facesCount: Int = faceDescriptors.size

    fun getTriangleByIndex(index: Int): Triangle {
        require(index in 0..faceDescriptors.size)
        return Triangle(
            points[faceDescriptors[index].pointIndexes[0]],
            points[faceDescriptors[index].pointIndexes[1]],
            points[faceDescriptors[index].pointIndexes[2]]
        )
    }

}