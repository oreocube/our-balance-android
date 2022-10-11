package com.ourbalance.feature.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorInt
import com.ourbalance.feature.R

class BalanceBar constructor(
    context: Context,
    attrs: AttributeSet? = null
) : View(context, attrs) {

    private var borderRect = RectF(0f, 0f, 0f, 0f)
    private var ratioRect = RectF(0f, 0f, 0f, 0f)
    private var borderPath = Path()
    private var ratioPath = Path()

    private val cornerRadius = 32f
    private var fillColor: Int = Color.BLACK
    private var ratio = 0
    private var w: Int = 0
    private var h: Int = 0

    private val strokePaint: Paint = Paint(ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 6f
        color = Color.BLACK
    }

    private val paint: Paint = Paint(ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
    }

    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.BalanceBar, 0, 0).apply {
            try {
                fillColor = getColor(R.styleable.BalanceBar_fillColor, 0xFF000000.toInt())
                ratio = getInteger(R.styleable.BalanceBar_ratio, 0)
            } finally {
                recycle()
            }
        }
    }

    fun setFillColor(@ColorInt color: Int) {
        fillColor = color
        invalidate()
    }

    fun setRatio(ratio: Int) {
        this.ratio = ratio
        drawRatioRect(ratio)
        invalidate()
    }

    private fun drawRatioRect(ratio: Int) {
        ratioRect =
            RectF(0f, 0f, w.toFloat() * ratio / 100, h.toFloat()).apply {
                inset(strokePaint.strokeWidth / 2, strokePaint.strokeWidth / 2)
            }

        ratioPath = Path().apply {
            addRect(ratioRect, Path.Direction.CW)
            op(borderPath, Path.Op.INTERSECT)
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        this.w = w
        this.h = h

        borderRect = RectF(0f, 0f, w.toFloat(), h.toFloat()).apply {
            inset(strokePaint.strokeWidth / 2, strokePaint.strokeWidth / 2)
        }

        borderPath = Path().apply {
            addRoundRect(borderRect, cornerRadius, cornerRadius, Path.Direction.CW)
        }

        drawRatioRect(ratio)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        with(canvas) {
            drawPath(
                ratioPath,
                paint.apply {
                    color = fillColor
                }
            )
            drawPath(ratioPath, strokePaint)
            drawPath(borderPath, strokePaint)
        }
    }
}
