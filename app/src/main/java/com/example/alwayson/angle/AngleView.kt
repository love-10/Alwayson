package com.example.alwayson.angle

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Point
import android.util.AttributeSet
import android.view.View

class AngleView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {
    private val tl by lazy {
        Point(width / 4, height / 4)
    }
    private val tr by lazy {
        Point(width * 3 / 4, height / 4)
    }
    private val bl by lazy {
        Point(width / 4, height * 3 / 4)
    }
    private val br by lazy {
        Point(width * 3 / 4, height * 3 / 4)
    }
    private val paint by lazy {
        Paint().apply {
            color = Color.RED           // 设置画笔颜色
            strokeWidth = 10f           // 设置线条宽度
            style = Paint.Style.FILL_AND_STROKE  // 设置画笔样式为描边
        }
    }
    private val validLinePath1 by lazy {
        Path().apply {
            moveTo(tl.x.toFloat(), tl.y.toFloat())
            lineTo(bl.x.toFloat(), bl.y.toFloat())
            close()
        }
    }
    private val validLinePath2 by lazy {
        Path().apply {
            moveTo(tr.x.toFloat(), tr.y.toFloat())
            lineTo(br.x.toFloat(), br.y.toFloat())
            close()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(validLinePath1, paint)
        canvas.drawPath(validLinePath2, paint)
    }
}