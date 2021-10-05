package saini.ayush.chart

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.view.animation.DecelerateInterpolator
import kotlin.math.min

class DoughnutChart(context: Context, attrs: AttributeSet) : View(context, attrs) {


    private val paint = Paint()
    private var size = 0
    private var animator: ValueAnimator? = null
    private var currentSweepAngle = 0f
    private var outerBorderSize = 0f
    private var innerBorderSize = 0f
    private var outerBorderColor = Color.parseColor("#000000")
    private var innerBorderColor = Color.parseColor("#000000")
    private var rectF: RectF = RectF(0f, 0f, 0f, 0f)

    companion object {
        const val DOUGHNUT_CHART_START_ANGLE = -90f
        const val DOUGHNUT_CHART_ANIMATION_DURATION = 650L
    }

    private var doughnutChartList = mutableListOf<Slice>()
    private var doughnutSize = 10f

    init {
        paint.isAntiAlias = true
        computeSlices()
    }

    fun submitList(
        doughnutChartList: ArrayList<Slice>,
        doughnutSize : Float = this.doughnutSize
    ) {
        this.doughnutSize = doughnutSize
        this.doughnutChartList = doughnutChartList
        computeSlices()
    }

    fun setBorders(
        outerBorderSize: Float = this.outerBorderSize,
        outerBorderColor: Int = this.outerBorderColor,
        innerBorderSize: Float = this.innerBorderSize,
        innerBorderColor: Int = this.innerBorderColor,
    ) {
        this.outerBorderSize = outerBorderSize
        this.outerBorderColor = outerBorderColor
        this.innerBorderSize = innerBorderSize
        this.innerBorderColor = innerBorderColor
        startDoughnutChartAnimation()
    }

    private fun computeSlices() {
        if (doughnutChartList.isEmpty()) return
        var startAngle = 0f

        for (i in 0 until doughnutChartList.size) {
            val sweepSize = 3.6f * doughnutChartList[i].percentage
            doughnutChartList[i].startAngle = startAngle
            doughnutChartList[i].sweepSize = sweepSize
            startAngle += sweepSize
        }
        startDoughnutChartAnimation()
    }

    private fun startDoughnutChartAnimation() {
        animator?.cancel()
        animator = ValueAnimator.ofFloat(0f, 360f).apply {
            duration = DOUGHNUT_CHART_ANIMATION_DURATION
            interpolator = DecelerateInterpolator()
            addUpdateListener { valueAnimator ->
                currentSweepAngle = valueAnimator.animatedValue as Float
                invalidate()
            }
        }
        animator?.start()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawPieChart(canvas)
    }

    private fun drawPieChart(canvas: Canvas) {

        val path = Path()
        path.addCircle(
            size / 2f, size / 2f,
            size / 2f - doughnutSize - 1.5f*innerBorderSize - outerBorderSize, Path.Direction.CCW
        )
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            canvas.clipPath(path, Region.Op.DIFFERENCE)
        } else {
            canvas.clipOutPath(path)
        }

        paint.style = Paint.Style.FILL
        var temp = 0f
        doughnutChartList.forEach { slice ->
            if (currentSweepAngle >= slice.startAngle) {
                paint.color = slice.color
                canvas.drawArc(
                    rectF,
                    DOUGHNUT_CHART_START_ANGLE + temp,
                    currentSweepAngle - slice.startAngle,
                    true,
                    paint
                )
                temp += slice.sweepSize
            }
        }

        if (outerBorderSize > 0f) {
            val radius = size / 2f - outerBorderSize
            paint.color = outerBorderColor
            paint.style = Paint.Style.STROKE
            paint.strokeWidth = outerBorderSize
            canvas.drawCircle(size / 2f, size / 2f, radius, paint)
        }

        if (innerBorderSize > 0f) {
            val radius = size / 2f - outerBorderSize - doughnutSize - innerBorderSize
            paint.color = innerBorderColor
            paint.style = Paint.Style.STROKE
            paint.strokeWidth = innerBorderSize
            canvas.drawCircle(size / 2f, size / 2f, radius, paint)
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        size = min(measuredWidth, measuredHeight)
        setMeasuredDimension(size, size)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        rectF = RectF(
            (measuredWidth - size + outerBorderSize) / 2f,
            (measuredHeight - size + outerBorderSize) / 2f,
            (measuredWidth + size - outerBorderSize) / 2f,
            (measuredHeight + size - outerBorderSize) / 2f,
        )
    }
}