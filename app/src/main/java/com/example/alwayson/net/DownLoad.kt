package com.example.alwayson.net

import android.util.Log
import androidx.fragment.app.FragmentActivity
import com.example.alwayson.bgHandler
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream

object DownLoad {

    fun downloadFile(activity: FragmentActivity, fileUrl: String, fileName: String) {
        val call = RetrofitClient.fileDownloadService.downloadFile(fileUrl)

        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                bgHandler.post{
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