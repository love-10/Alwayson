package com.example.alwayson.net

import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.PartMap
import retrofit2.http.Streaming
import retrofit2.http.Url

interface FileDownloadService {
    @GET
    @Streaming
    fun downloadFile(@Url fileUrl: String): Call<ResponseBody>

    @Multipart
    @POST("upload.php")
    fun uploadFile(
        @Part file: MultipartBody.Part,
        @Part("description") description: RequestBody
    ): Call<UploadResponse>

    @Multipart
    @POST("upload_more.php")
    fun uploadFiles(@Part files: List<MultipartBody.Part>): Call<UploadResponse>

    @POST("create_user.php") // PHP 处理请求的脚本路径
    fun createUser(@Body user: User): Call<ApiResponse>
}