package com.example.alwayson.angle

import android.app.Activity
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import com.example.alwayson.databinding.AngleActivityBinding
import kotlin.math.roundToInt

class AngleActivity : Activity(), SensorEventListener {
    private lateinit var binding: AngleActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AngleActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY)?.also {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_UI)
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_GRAVITY) {
            val gravityZ = event.values[2]
            val z = ARC * gravityZ / G
            // 处理重力数据
            Log.d("GravitySensor", "Z: ${z.roundToInt()}")
        }
    }

    companion object {
        const val G = 9.8
        const val ARC = 90
    }
}