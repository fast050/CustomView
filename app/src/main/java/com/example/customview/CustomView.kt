package com.example.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import kotlin.math.pow

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
    private var squareSize: Int = 0

    //property of circle
    private var radius = 100f
    private var cx: Float = 0f
    private var cy: Float = 0f

    init {
        if (attrs != null) {

            //to use your xml attrs in your xml
            val arrayType = getContext().obtainStyledAttributes(attrs, R.styleable.CustomView)

            squareColor = arrayType.getColor(R.styleable.CustomView_square_color, SHAPE_INIT_COLOR)
            squareSize =
                arrayType.getDimensionPixelSize(R.styleable.CustomView_square_size, SHAPE_SIZE)

            paint.style = Paint.Style.FILL
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


        if (cx == 0f || cy == 0f) {
            cx = (width / 2).toFloat()
            cy = (height / 2).toFloat()
        }

        //cx,cy is represent the space from X,Y axis
        canvas?.drawCircle(cx, cy, radius, paint)

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val boolean = super.onTouchEvent(event)


        when (event?.action) {



            //
            MotionEvent.ACTION_DOWN->{

//                val x = event.x
//                val y = event.y
//
//                if( (rec.left<x && x>rec.right) && (rec.top<y && y>rec.bottom)){
//                    radius += 10f
//
//                    postInvalidate()
//
                    return true
//                }
            }

            //drag and drop feature
            MotionEvent.ACTION_MOVE -> {

                val x = event.x
                val y = event.y

                val dx = (x - cx).pow(2)
                val dy = (y - cy).pow(2)

                if (dx + dy < (radius).pow(2)) {
                    cx = x
                    cy = y
                    // circle touched
                    postInvalidate()

                    return true
                }

                return boolean
            }


        }


        return boolean
    }


    companion object {
        const val SHAPE_INIT_COLOR = Color.GREEN
        const val SHAPE_SIZE = 100
    }
}