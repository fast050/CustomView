package com.example.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View

class CustomView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val rec: Rect = Rect()

    //to smooth the text drawing (look it up)
    private var paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    //add color to your shape in your xml
    private var squareColor: Int = 0
    //add size of your shape in your xml
    private var squareSize: Int =0

    init {
        if (attrs != null) {

            //to use your xml attrs in your xml
            val arrayType = getContext().obtainStyledAttributes(attrs, R.styleable.CustomView)

            squareColor = arrayType.getColor(R.styleable.CustomView_square_color, SHAPE_INIT_COLOR)
            squareSize =
                arrayType.getDimensionPixelSize(R.styleable.CustomView_square_size, SHAPE_SIZE)

            paint.color = squareColor
            //to clear the arrayType object
            arrayType.recycle()
        }
    }

    fun swapColor() {
        paint.color = if (paint.color == squareColor)
            Color.RED
        else
            squareColor

        //don't block the ui
        postInvalidate()

        //invalidate()
    }

    //it call a lot pre second
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        //rec.top is represent the space from X,Y axis
        rec.top = 0
        rec.left = 0
        rec.bottom = rec.top + squareSize
        rec.right = rec.left + squareSize

        canvas?.drawRect(rec, paint)

        //cx,cy is represent the space from X,Y axis
        val radius = 100f
        val cx = width - radius
        val cy= (rec.width()/2).toFloat()
        canvas?.drawCircle(cx,cy,radius,paint)

    }

    companion object {
        const val SHAPE_INIT_COLOR = Color.GREEN
        const val SHAPE_SIZE = 100
    }
}