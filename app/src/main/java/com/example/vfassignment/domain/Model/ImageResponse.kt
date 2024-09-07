package com.example.vfassignment.domain.Model


data class ImageResponse(
    val data: List<ImageData>
)

data class ImageData(
    val imageUrl: String
)