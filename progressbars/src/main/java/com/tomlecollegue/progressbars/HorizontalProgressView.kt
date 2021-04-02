package com.tomlecollegue.progressbars

import android.animation.ValueAnimator
import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.graphics.Typeface
import android.view.animation.DecelerateInterpolator
import com.tomlecollegue.progressbars.R


class HorizontalProgressView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var viewWidth: Float = 0.0f
    private var viewHeight: Float = 0.0f

    private var defaultProgressColor = Color.parseColor("#55E552")
    private var defaultBackGroundColor = Color.parseColor("#EFEFEF")

    private val DP_IN_PX = Resources.getSystem().displayMetrics.density
    private val DEFAULT_PROGRESS_STROKE_WIDTH = 16 * DP_IN_PX
    private var strokeWithProgress = DEFAULT_PROGRESS_STROKE_WIDTH

    private var widthBar: Float = 0.0f
    private var maxWidthBar = 0.0f
    private var centerX : Float = 0f
    private var centerY : Float = 0f



    private val progressPaint: Paint
    private val progressTextPaint: Paint
    private val backgroundProgressPaint: Paint

    private val animationInterpolator by lazy { DecelerateInterpolator() }

    /**
     * Represents current progress from 0 to 100.
     */
    var progress: Int = 0
        set(value) {
            field = value
            ValueAnimator.ofFloat(widthBar, maxWidthBar / 100f * progress).apply {
                interpolator = animationInterpolator
                duration = 300
                addUpdateListener { animation ->
                    widthBar = animation.animatedValue as Float
                    invalidate()
                }
                start()
            }
        }

    init {
        isClickable = true

        val typedArray = context.obtainStyledAttributes(attrs,R.styleable.Progress)
        val colorBackground= typedArray.getColor(R.styleable.Progress_colorBackground, defaultBackGroundColor)
        val colorProgress= typedArray.getColor(R.styleable.Progress_colorProgress, defaultProgressColor)
        val progressDefault = typedArray.getInteger(R.styleable.Progress_defaultValue, 0)
        strokeWithProgress = typedArray.getDimension(R.styleable.Progress_strokeWidth, DEFAULT_PROGRESS_STROKE_WIDTH)

        progressPaint = Paint().apply {
            isAntiAlias = true
            color = colorProgress
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
            strokeWidth = strokeWithProgress
        }

        backgroundProgressPaint = Paint().apply {
            isAntiAlias = true
            color = colorBackground
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
            strokeWidth = strokeWithProgress
        }

        progressTextPaint = Paint().apply {
            isAntiAlias = true
            color = Color.BLACK
            strokeWidth = 0f
            textAlign = Paint.Align.CENTER
            typeface = Typeface.create("cabin", Typeface.BOLD)
            textSize = 120f
        }
        progress = progressDefault
    }

    /**
     * This is called during layout when the size of this view has changed. If
     * the view was just added to the view hierarchy, it is called with the old
     * values of 0. The code determines the drawing bounds for the custom view.
     *
     * @param width    Current width of this view.
     * @param height    Current height of this view.
     * @param oldWidth Old width of this view.
     * @param oldHeight Old height of this view.
     */
    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        viewWidth = width.toFloat()
        viewHeight = height.toFloat()

        centerX = viewWidth/ 2f
        centerY = viewHeight / 2f

        maxWidthBar = viewWidth - (2*strokeWithProgress)

        // Update init value progressBar
        progress = progress
    }

    /**
     * Renders view content: an outer circle to serve as the "dial",
     * and a smaller black circle to server as the indicator.
     * The position of the indicator is based on fanSpeed.
     *
     * @param canvas The canvas on which the background will be drawn.
     */
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val startX = centerX - (viewWidth/2) + strokeWithProgress
        val startY = centerY
        val stopY = centerY

        // background
        val stopX = centerX + (viewWidth/2) - strokeWithProgress
        canvas.drawLine(startX,startY, stopX, stopY, backgroundProgressPaint )

        // progressBar
        var stopXProgress = widthBar + strokeWithProgress
        print(stopXProgress)

        if(progress != 0){
            canvas.drawLine(startX,startY, stopXProgress, stopY, progressPaint )
        }



    }}