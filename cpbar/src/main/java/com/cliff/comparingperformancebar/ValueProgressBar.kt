package com.cliff.comparingperformancebar

import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import androidx.annotation.FloatRange
import androidx.core.content.res.ResourcesCompat
import java.lang.Math.abs

class ValueProgressBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val progressLeftPaint: Paint = Paint()
    private val progressRightPaint: Paint = Paint()
    private var percentageTextPaint = TextPaint()

    private lateinit var containerRectF: RectF
    private var currentLeftValue: Float = 0.0f
    private var currentRightValue: Float = 0.0f
    private var progressPadding = 5.0f
    private var spaceAtCenter = 0.0f

    private var typedArray: TypedArray? = null

    init {
        typedArray = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.ValueProgressBar,
            0,
            0
        )
        initAttributes()
    }

    private fun initAttributes() {
        typedArray?.apply {
            currentLeftValue =
                abs(getInt(R.styleable.ValueProgressBar_vpb_valueLeft, 50))
                    .toFloat()
            currentRightValue =
                abs(getInt(R.styleable.ValueProgressBar_vpb_valueRight, 50))
                    .toFloat()
            progressRightPaint.color =
                getColor(R.styleable.ValueProgressBar_vpb_progressRightColor, Color.RED)
            progressLeftPaint.color =
                getColor(R.styleable.ValueProgressBar_vpb_progressLeftColor, Color.BLACK)

            percentageTextPaint.color = getColor(
                R.styleable.ValueProgressBar_vpb_textColor,
                Color.WHITE
            )


            percentageTextPaint.textSize =
                getDimensionPixelSize(R.styleable.ValueProgressBar_vpb_textSize, 32).toFloat()

            val fontId = getResourceId(R.styleable.ValueProgressBar_android_fontFamily, 0)
            if (fontId != 0) {
                percentageTextPaint.typeface = ResourcesCompat.getFont(context, fontId)
            } else {
                percentageTextPaint.typeface = Typeface.create(Typeface.MONOSPACE, Typeface.BOLD);
            }
            recycle()
        }
    }

    fun setLeftValue(progressValue: Float = 10f) {
        currentLeftValue = progressValue
    }

    fun setRightValue(progressValue: Float = 10f) {
        currentLeftValue = progressValue
    }

    fun setValues(progressLeftValue: Float = 10f, progressRightValue: Float = 10f) {
        currentLeftValue = progressLeftValue
        currentRightValue = progressRightValue
        invalidate()
    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        containerRectF = RectF()
        containerRectF.set(0f, 0f, w.toFloat(), h.toFloat())
        spaceAtCenter = ((0.05 * containerRectF.right) / 2).toFloat()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawProgressBar(canvas)


        val textLeftSizeWidth =
            percentageTextPaint.measureText("%d".format(currentLeftValue.toInt()))

        val textRightSizeWidth =
            percentageTextPaint.measureText("%d".format(currentRightValue.toInt()))

        canvas?.drawText(
            "%d".format(currentLeftValue.toInt()),
            containerRectF.left + textLeftSizeWidth / 2,
            containerRectF.centerY() + percentageTextPaint.textSize / 2,
            percentageTextPaint
        )

        canvas?.drawText(
            "%d".format(currentRightValue.toInt()),
            containerRectF.right - textRightSizeWidth - (textRightSizeWidth / 2),
            containerRectF.centerY() + percentageTextPaint.textSize / 2,
            percentageTextPaint
        )
    }

    private fun drawProgressBar(canvas: Canvas?) {
        val total = currentLeftValue + currentRightValue

        val pathLeft = drawLeftProgress(
            containerRectF,
            (((currentLeftValue / total) * containerRectF.right)),
            progressPadding, spaceAtCenter
        )

        val pathRight = drawRightProgress(
            containerRectF,
            ((currentRightValue / total) * containerRectF.right),
            progressPadding, spaceAtCenter
        )
        canvas?.drawPath(pathLeft, progressLeftPaint)
        canvas?.drawPath(pathRight, progressRightPaint)
    }


    private fun drawLeftProgress(
        rectF: RectF,
        width: Float,
        padding: Float,
        spaceInTheMiddle: Float
    ): Path {
        val path = Path()
        with(rectF) {
            path.moveTo(left + padding, padding)
            path.lineTo(left + padding, bottom - padding)
            path.lineTo(
                left + width + (right * 0.05f) - spaceInTheMiddle,
                bottom - padding
            )
            path.lineTo(
                left + width - (right * 0.05f) - spaceInTheMiddle,
                padding
            )
        }
        path.close()
        return path
    }

    private fun drawRightProgress(
        rectF: RectF,
        width: Float,
        padding: Float,
        spaceInTheMiddle: Float
    ): Path {
        val path = Path()
        with(rectF) {
            path.moveTo(right - padding, padding)
            path.lineTo(right - padding, bottom - padding)
            path.lineTo(
                right - width + (right * 0.05f) + spaceInTheMiddle,
                bottom - padding
            )
            path.lineTo(
                right - width - (right * 0.05f) + spaceInTheMiddle,
                padding
            )
        }
        path.close()
        return path
    }
}