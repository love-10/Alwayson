package com.example.alwayson.net

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentActivity
import com.example.alwayson.bgHandler
import com.example.alwayson.databinding.ActivityNetBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class MainActivity : FragmentActivity() {
    private lateinit var binding: ActivityNetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNetBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.download.setOnClickListener {
            bgHandler.post {
                Api.downloadFile(this, "http://192.168.0.103:8080/1.mp4", "1.mp4")
            }
        }

        binding.upload.setOnClickListener {
            bgHandler.post {
                Api.uploadFile(
                    File(getExternalFilesDir("Download")!!.absoluteFile, "1.mp4"),
                    "6"
                )
            }
        }

        binding.uploadMore.setOnClickListener {
            bgHandler.post {
                Api.uploadFiles(
                    listOf(
                        File(getExternalFilesDir("Download")!!.absoluteFile, "d.html"),
                        File(getExternalFilesDir("Download")!!.absoluteFile, "1.mp4")
                    )
                )
            }
        }

        binding.createUser.setOnClickListener {
            bgHandler.post {
                // 创建要发送的 User 对象
                val user = User(name = "John Doe", age = 30, email = "john.doe@example.com")

                // 发起请求
                RetrofitClient.fileDownloadService.createUser(user).enqueue(object :
                    Callback<ApiResponse> {
                    override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                        if (response.isSuccessful) {
                            response.body()?.let {
                                Log.d("Retrofit", "Success: ${it.message}")
                            }
                        } else {
                            Log.e("Retrofit", "Error: ${response.errorBody()?.string()}")
                        }
                    }

                    override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                        Log.e("Retrofit", "Failure: ${t.message}")
                    }
                })
            }
        }
    }
}