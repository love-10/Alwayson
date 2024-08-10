package com.example.alwayson.net

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {
    private const val BASE_URL =
        "http://192.168.0.103:8080/" // base URL is required but can be different from the file URL
    var gson: Gson = GsonBuilder()
        .setLenient()
        .create()
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val fileDownloadService: FileDownloadService = retrofit.create(FileDownloadService::class.java)
}