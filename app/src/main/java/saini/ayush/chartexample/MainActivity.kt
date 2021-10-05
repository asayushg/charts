package saini.ayush.chartexample

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import saini.ayush.chart.DoughnutChart
import saini.ayush.chart.Slice

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = ArrayList<Slice>()
        list.add(
            Slice(
                20f,
                Color.RED
            )
        )
        list.add(
            Slice(
                25f,
                Color.BLUE
            )
        )
        list.add(
            Slice(
                15f,
                Color.GREEN
            )
        )
        list.add(
            Slice(
                35f,
                Color.YELLOW
            )
        )
        list.add(
            Slice(
                5f,
                Color.MAGENTA
            )
        )

        val doughnutChart = findViewById<DoughnutChart>(R.id.doughnutChart)

        findViewById<Button>(R.id.btn).setOnClickListener {
            doughnutChart.visibility = View.VISIBLE
            doughnutChart.submitList(
                doughnutChartList = list,
                doughnutSize = 100f
            )
            doughnutChart.setBorders(
                outerBorderSize = 10f,
                outerBorderColor = Color.LTGRAY,
                innerBorderSize = 10f,
                innerBorderColor = Color.LTGRAY
            )
        }
    }
}