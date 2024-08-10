package com.example.alwayson.net

import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.example.alwayson.bgHandler
import com.example.alwayson.databinding.ActivityNetBinding
import java.io.File

class MainActivity : FragmentActivity() {
    private lateinit var binding: ActivityNetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNetBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.download.setOnClickListener {
            bgHandler.post {
                DownLoad.downloadFile(this, "http://192.168.0.103:8080/1.mp4", "1.mp4")
            }
        }

        binding.upload.setOnClickListener {
            bgHandler.post {
                DownLoad.uploadFile(
                    File(getExternalFilesDir("Download")!!.absoluteFile, "d.html"),
                    "6"
                )
            }
        }
    }
}