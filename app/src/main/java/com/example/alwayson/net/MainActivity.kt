package com.example.alwayson.net

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.example.alwayson.databinding.ActivityNetBinding

class MainActivity : FragmentActivity() {
    private lateinit var binding: ActivityNetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNetBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.download.setOnClickListener {
            Toast.makeText(this, "下载", Toast.LENGTH_SHORT).show()
        }
    }
}