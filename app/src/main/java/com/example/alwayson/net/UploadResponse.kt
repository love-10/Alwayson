package com.example.alwayson.net

data class UploadResponse(
    val status: String,
    val file_name: String,
    val file_size: String,
    val file_type: String,
    val message: String,
)
