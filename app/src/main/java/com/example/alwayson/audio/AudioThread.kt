package com.example.alwayson.audio

import android.annotation.SuppressLint
import android.media.AudioRecord
import android.media.MediaRecorder

@SuppressLint("MissingPermission")
class AudioThread : Thread() {
    private var audioRecord: AudioRecord
    private var bufferSize = 0

    init {
        bufferSize = AudioRecord.getMinBufferSize(
            AudioConfig.SAMPLE_RATE,
            AudioConfig.CHANNEL_CONFIG, AudioConfig.ENCODING_CONFIG
        ) * AudioConfig.RECORD_AUDIO_BUFFER_TIMES

        audioRecord = AudioRecord(
            MediaRecorder.AudioSource.MIC, AudioConfig.SAMPLE_RATE,
            AudioConfig.CHANNEL_CONFIG, AudioConfig.ENCODING_CONFIG, bufferSize
        )
    }

    override fun run() {
        super.run()
        audioRecord.startRecording()
        while (audioRecord.recordingState == AudioRecord.RECORDSTATE_RECORDING) {
            val byteBuffer = ByteArray(bufferSize)
            val end = audioRecord.read(byteBuffer, 0, byteBuffer.size)

        }
    }
}