package com.lizpostudio.myapplication

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.util.AttributeSet
import android.view.View

private const val LINE_STROKE = 37f
private const val cRadius = 33f

class MaskView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    View(context, attrs, defStyleAttr) {


    var fillMask = mutableListOf<PointF>()

    private val screenDst = Resources.getSystem().displayMetrics.density
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = Color.rgb(53, 139, 57)
        typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
        strokeWidth = screenDst* LINE_STROKE
       // textSize =screenDst* TEXT_HEIGHT_SP
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

           if (fillMask.size > 1) {
            for (index in 0..fillMask.lastIndex) {

            /*    canvas.drawLine(fillMask[index-1].x, fillMask[index-1].y,
                fillMask[index].x, fillMask[index].y, paint)*/

                canvas.drawCircle(fillMask[index].x, fillMask[index].y, cRadius, paint)
            }
        }


    }
}