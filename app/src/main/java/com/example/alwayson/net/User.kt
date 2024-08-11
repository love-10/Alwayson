package com.example.alwayson.net

// 请求数据模型
data class User(
    val name: String,
    val age: Int,
    val email: String
)

// 响应数据模型
data class ApiResponse(
    val success: Boolean,
    val message: String
)