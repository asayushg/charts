package saini.ayush.chart

data class Slice(
    var percentage: Float,
    var color: Int,
    var startAngle: Float = 0f,
    var sweepSize: Float = 0f,
)
