package com.example.alwayson.audio

import java.io.File
import java.io.FileOutputStream
import java.io.IOException

object AudioUtils {
    fun saveByteArrayToFile(byteArray: ByteArray, end: Int, filePath: String): File? {
        val file = File(filePath)
        try {
            // 创建文件输出流
            val fos = FileOutputStream(file)
            // 写入字节数组到文件
            fos.write(byteArray, 0, end)
            fos.close()
            return file
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

}