# Charts
[![Release](https://img.shields.io/github/release/asayushg/charts.svg?style=flat)](https://jitpack.io/#asayushg/charts)
[![](https://www.jitpack.io/v/asayushg/charts.svg)](https://www.jitpack.io/#asayushg/charts)

### Gradle Setup

Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://www.jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.asayushg:charts:1.0.1'
	}

<h2 id="examples">Examples :eyes:</h2>

<img src="pie-chart-example.gif" width="250"/> <img src="pie-chart-border-example.gif" width="250"/>
<img src="doughnut-chart-example.gif" width="250"/> <img src="doughnut-chart-border-example.gif" width="250"/>

<h2 id="documentation">Documentation :notebook_with_decorative_cover:</h2>
<h4> 1. Pie Chart </h4>

Add the PieChart View in your layout as:
``` 
<saini.ayush.chart.PieChart
    android:id="@+id/pieChart"
    android:layout_width="125dp"
    android:layout_height="125dp"
    .../>
```

Create a list of Slice with percentage and color and submit to PieChart view as:
``` 
val list = ArrayList<Slice>()
list.add(
	Slice(
    	   percentage = 20f,
           color = Color.RED
        )
    )
// add all the slices
val pieChart = findViewById<PieChart>(R.id.pieChart)
pieChart.submitList(pieChartList = list)
// set borderSize and borderColor
pieChart.setBorder(10f, Color.BLACK)
```

<h4> 2. Doughnut Chart </h4>

Add the DoughnutChart View in your layout as:
```
<saini.ayush.chart.DoughnutChart
    android:id="@+id/doughnutChart"
    android:layout_width="125dp"
    android:layout_height="125dp"
    .../>
```

Create a list of Slice with percentage and color and submit to DoughnutChart view as:
```
val list = ArrayList<Slice>()
list.add(
	Slice(
    	   percentage = 20f,
           color = Color.RED
        )
    )
// add all the slices
val doughnutChart = findViewById<DoughnutChart>(R.id.doughnutChart)
// submit list and doughnutChart Size
doughnutChart.submitList(
        doughnutChartList = list,
        doughnutSize = 100f
    )
// set inner border
    doughnutChart.setBorders(
        innerBorderSize = 10f,
        innerBorderColor = Color.BLACK,
    )
// set outer border
   doughnutChart.setBorders(
        outerBorderSize = 10f,
        outerBorderColor = Color.BLACK,
   )
// set both borders
    doughnutChart.setBorders(
        outerBorderSize = 10f,
        outerBorderColor = Color.BLACK,
        innerBorderSize = 10f,
        innerBorderColor = Color.BLACK
    )


```

### Charts Available
- [x] Pie Chart
- [x] Doughnut Chart
- [ ] Bar Chart
- [ ] Line Chart
- [ ] Area Chart
- [ ] Scatter Plot
