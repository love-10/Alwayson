package com.example.alwayson.angle

import android.app.Activity
import android.os.Bundle
import com.example.alwayson.databinding.AngleActivityBinding

class AngleActivity : Activity() {
    private lateinit var binding: AngleActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AngleActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}