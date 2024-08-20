package com.example.alwayson.audio

import android.media.AudioFormat
import android.os.Environment

object AudioConfig {
    const val SAMPLE_RATE = 16000
    const val ENCODING_BIT = 16
    const val ENCODING_CONFIG = AudioFormat.ENCODING_PCM_16BIT
    const val CHANNEL_COUNT = 1
    const val CHANNEL_CONFIG = AudioFormat.CHANNEL_IN_MONO
    const val RECORD_AUDIO_BUFFER_TIMES = 20
    val recordDir = "${Environment.getExternalStorageDirectory().absolutePath}/Record/"
}