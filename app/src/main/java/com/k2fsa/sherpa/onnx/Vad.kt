// Copyright (c)  2023  Xiaomi Corporation
package com.k2fsa.sherpa.onnx

import android.content.res.AssetManager
import android.util.Log
import com.example.alwayson.audio.AudioConfig

data class SileroVadModelConfig(
    var model: String,
    var threshold: Float = 0.5F,
    var minSilenceDuration: Float = 0.25F,
    var minSpeechDuration: Float = 0.25F,
    var windowSize: Int = 512,
)

data class VadModelConfig(
    var sileroVadModelConfig: SileroVadModelConfig,
    var sampleRate: Int = 16000,
    var numThreads: Int = 1,
    var provider: String = "cpu",
    var debug: Boolean = false,
)

class Vad(
    assetManager: AssetManager? = null,
    var config: VadModelConfig,
) {
    private val ptr: Long

    init {
        ptr = if (assetManager != null) {
            newFromAsset(assetManager, config)
        } else {
            newFromFile(config)
        }
    }

    protected fun finalize() {
        delete(ptr)
    }

    fun acceptWaveform(samples: FloatArray) = acceptWaveform(ptr, samples)

    fun empty(): Boolean = empty(ptr)
    fun pop() = pop(ptr)

    // return an array containing
    // [start: Int, samples: FloatArray]
    fun front() = front(ptr)

    fun clear() = clear(ptr)

    fun isSpeechDetected(): Boolean = isSpeechDetected(ptr)

    fun reset() = reset(ptr)

    fun flush() = flush(ptr)

    private external fun delete(ptr: Long)

    private external fun newFromAsset(
        assetManager: AssetManager,
        config: VadModelConfig,
    ): Long

    private external fun newFromFile(
        config: VadModelConfig,
    ): Long

    private external fun acceptWaveform(ptr: Long, samples: FloatArray)
    private external fun empty(ptr: Long): Boolean
    private external fun pop(ptr: Long)
    private external fun clear(ptr: Long)
    private external fun front(ptr: Long): Array<Any>
    private external fun isSpeechDetected(ptr: Long): Boolean
    private external fun reset(ptr: Long)
    private external fun flush(ptr: Long)

    companion object {
        init {
            try {
                System.loadLibrary("sherpa-onnx-jni")
            } catch (e: Exception) {
                Log.d("xxxxx", "dddddd: $e")
            }

        }
    }
}

// directory
fun getVadModelConfig(): VadModelConfig {
    return VadModelConfig(
        sileroVadModelConfig = SileroVadModelConfig(
            model = "silero_vad.onnx",
            threshold = 0.5F,
            minSilenceDuration = 0.25F,
            minSpeechDuration = 0.25F,
            windowSize = 512,
        ),
        sampleRate = AudioConfig.SAMPLE_RATE,
        numThreads = 1,
        provider = "cpu",
    )
}
