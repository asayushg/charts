package saini.ayush.chartexample

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import saini.ayush.chart.PieChart

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = ArrayList<PieChart.Slice>()
        list.add(
            PieChart.Slice(
                20f,
                Color.RED
            )
        )
        list.add(
            PieChart.Slice(
                25f,
                Color.BLUE
            )
        )
        list.add(
            PieChart.Slice(
                15f,
                Color.GREEN
            )
        )
        list.add(
            PieChart.Slice(
                35f,
                Color.YELLOW
            )
        )
        list.add(
            PieChart.Slice(
                5f,
                Color.MAGENTA
            )
        )

        findViewById<Button>(R.id.btn).setOnClickListener {
            findViewById<PieChart>(R.id.pieChart).visibility = View.VISIBLE
            findViewById<PieChart>(R.id.pieChart).submitList(
                pieChartList = list, 10f, Color.LTGRAY
            )

        }
    }
}