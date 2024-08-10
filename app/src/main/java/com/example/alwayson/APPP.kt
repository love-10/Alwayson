package com.example.alwayson

import android.os.Handler
import android.os.HandlerThread

val bgThread = HandlerThread("bgthread").apply { start() }
val bgHandler = Handler(bgThread.looper)