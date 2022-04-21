package com.example.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View

class CustomView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet?,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var rec: Rect = Rect()

    //to smooth the text drawing (look it up)
    private var paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        paint.color = Color.GREEN
    }

    fun swapColor() {
        paint.color = if (paint.color == Color.GREEN)
            Color.RED
        else
            Color.GREEN

        //dont block the ui
        postInvalidate()

        //invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        //rec.top is represent the space from X,Y axis
        rec.top = 0
        rec.left = 0
        rec.bottom = rec.top + SHAPE_SIZE
        rec.right = rec.left + SHAPE_SIZE


        canvas?.drawRect(rec, paint)
    }

    companion object {
        const val SHAPE_SIZE = 100
    }
}