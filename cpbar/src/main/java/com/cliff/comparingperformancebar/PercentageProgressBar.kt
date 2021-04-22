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

class PercentageProgressBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val progressLeftPaint: Paint = Paint()
    private val progressRightPaint: Paint = Paint()
    private var percentageTextPaint = TextPaint()

    private lateinit var containerRectF: RectF
    private var currentProgressValue: Float = 0.0f
    private var progressPadding = 5.0f
    private var spaceAtCenter = 0.0f

    private var typedArray: TypedArray? = null

    init {
        typedArray = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.PercentageProgressBar,
            0,
            0
        )
        initAttributes()
    }

    private fun initAttributes() {
        typedArray?.apply {
            currentProgressValue =
                abs(getInt(R.styleable.PercentageProgressBar_percentage, 50)).coerceIn(0, 100)
                    .toFloat()
            progressRightPaint.color =
                getColor(R.styleable.PercentageProgressBar_progressRightColor, Color.RED)
            progressLeftPaint.color =
                getColor(R.styleable.PercentageProgressBar_progressLeftColor, Color.BLACK)

            percentageTextPaint.color = getColor(
                R.styleable.PercentageProgressBar_textColor,
                Color.WHITE
            )

            // percentageTextPaint.textSize = resources.getDimension(R.dimen.default_text_size)
            percentageTextPaint.textSize =
                getDimensionPixelSize(R.styleable.PercentageProgressBar_textSize, 32).toFloat()

            val fontId = getResourceId(R.styleable.PercentageProgressBar_android_fontFamily, 0)
            if (fontId != 0) {
                percentageTextPaint.typeface = ResourcesCompat.getFont(context, fontId)
            } else {
                percentageTextPaint.typeface = Typeface.create(Typeface.MONOSPACE, Typeface.BOLD);
            }
            recycle()
        }
    }

    fun setProgress(@FloatRange(from = 0.0, to = 100.0) progressValue: Float = 10f) {
        currentProgressValue = progressValue
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


        val textSizeWidth =
            percentageTextPaint.measureText("%s%%".format(currentProgressValue.toInt()))

        //if (currentProgressValue >= 90)
            canvas?.drawText(
                "%s%%".format(currentProgressValue.toInt()),
                containerRectF.left + textSizeWidth / 2,
                containerRectF.centerY() + percentageTextPaint.textSize / 2,
                percentageTextPaint
            )

       // if (currentProgressValue <= 10)
            canvas?.drawText(
                "%s%%".format(100 - currentProgressValue.toInt()),
                containerRectF.right - textSizeWidth - (textSizeWidth / 2),
                containerRectF.centerY() + percentageTextPaint.textSize / 2,
                percentageTextPaint
            )
    }

    private fun drawProgressBar(canvas: Canvas?) {
        val pathLeft = drawLeftProgress(
            containerRectF,
            ((currentProgressValue / 100.0f) * containerRectF.right),
            progressPadding, spaceAtCenter
        )

        val pathRight = drawRightProgress(
            containerRectF,
            ((currentProgressValue / 100.0f) * containerRectF.right),
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
                left + width + (right * 0.05f) + spaceInTheMiddle,
                bottom - padding
            )
            path.lineTo(
                left + width - (right * 0.05f) + spaceInTheMiddle,
                padding
            )
        }
        path.close()
        return path
    }
}