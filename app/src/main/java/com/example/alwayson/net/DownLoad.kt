package com.example.alwayson.net

import android.net.Uri
import android.util.Log
import androidx.fragment.app.FragmentActivity
import com.example.alwayson.bgHandler
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream

object DownLoad {

    fun uploadFiles(fileList:List<File>) {
        val files = fileList.map { file ->
            val requestBody = file.asRequestBody("multipart/form-data".toMediaTypeOrNull())
            MultipartBody.Part.createFormData(file.name, file.name, requestBody)
        }

        val call = RetrofitClient.fileDownloadService.uploadFiles(files)

        call.enqueue(object : Callback<UploadResponse> {
            override fun onResponse(call: Call<UploadResponse>, response: Response<UploadResponse>) {
                if (response.isSuccessful) {
                    Log.d("Upload", "Files uploaded successfully ${response.body()}")
                } else {
                    Log.e("Upload", "Server returned an error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<UploadResponse>, t: Throwable) {
                Log.e("Upload", "Upload failed: ${t.message}")
            }
        })
    }

    fun uploadFile(file: File, descriptionText: String) {
        // 创建 RequestBody
        val requestFile = file.asRequestBody("video/mp4".toMediaTypeOrNull())

        // 创建 MultipartBody.Part，文件部分
        val body = MultipartBody.Part.createFormData("file", file.name, requestFile)

        // 创建 RequestBody，描述部分
        val description = descriptionText.toRequestBody("text/plain".toMediaTypeOrNull())

        // 发起请求
        RetrofitClient.fileDownloadService.uploadFile(body, description)
            .enqueue(object : retrofit2.Callback<UploadResponse> {
                override fun onResponse(
                    call: Call<UploadResponse>,
                    response: retrofit2.Response<UploadResponse>
                ) {
                    if (response.isSuccessful) {
                        Log.d("Upload", "Success: ${response.body()}")
                    } else {
                        Log.e("Upload", "Failed: ${response.errorBody()}")
                    }
                }

                override fun onFailure(call: Call<UploadResponse>, t: Throwable) {
                    Log.e("Upload", "Error: ${t.message}")
                }
            })
    }

    fun downloadFile(activity: FragmentActivity, fileUrl: String, fileName: String) {
        val call = RetrofitClient.fileDownloadService.downloadFile(fileUrl)

        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                bgHandler.post {
                    if (response.isSuccessful) {
                        response.body()?.let { body ->
                            val filePath = activity.getExternalFilesDir("DownLoad")!!.absolutePath
                            val file = File(filePath, fileName)

                            try {
                                val inputStream: InputStream = body.byteStream()
                                val outputStream: OutputStream = FileOutputStream(file)
                                val buffer = ByteArray(4096)
                                var bytesRead: Int

                                while (inputStream.read(buffer).also { bytesRead = it } != -1) {
                                    outputStream.write(buffer, 0, bytesRead)
                                }

                                outputStream.flush()
                                outputStream.close()
                                inputStream.close()

                                Log.d("Download", "File downloaded successfully: $file")
                            } catch (e: Exception) {
                                Log.e("Download", "Failed to save file: ${e.message}")
                            }
                        }
                    } else {
                        Log.e("Download", "Server returned an error: ${response.code()}")
                    }
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.e("Download", "Download failed: ${t.message}")
            }
        })
    }
}