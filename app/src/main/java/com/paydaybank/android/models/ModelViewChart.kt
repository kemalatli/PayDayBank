package com.paydaybank.android.models

import android.content.Context
import android.util.AttributeSet
import com.airbnb.epoxy.AfterPropsSet
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.google.android.material.card.MaterialCardView
import com.paydaybank.android.R
import com.paydaybank.android.models.helper.ChartColorManager
import com.paydaybank.data.repository.transaction.model.CategorizedSum
import kotlin.math.abs

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class ModelViewChart @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : MaterialCardView(context, attrs, defStyleAttr) {


    private val chart: BarChart

    private var categorizedSums:List<CategorizedSum> = listOf()

    init {
        // Inflate
        inflate(context, R.layout.model_view_chart, this)
        // Set views
        chart = findViewById(R.id.chart)
    }

    @ModelProp
    fun sums(categorizedSums:List<CategorizedSum>){
        this.categorizedSums = categorizedSums
        val dataSet = BarDataSet(
            categorizedSums.mapIndexed { index, categorizedSum ->
                BarEntry(
                    (index + 1) * 6f,
                    abs(categorizedSum.sum.toFloat())
                )
            },
            ""
        ).apply {
            setColors(
                ChartColorManager.getColors(categorizedSums).toIntArray(),
                context
            )
        }
        chart.apply {
            data = BarData(dataSet).apply {
                barWidth = 4f
                setDrawValues(false)
                setDrawMarkers(false)

            }
            legend.isEnabled = false
            setDrawBarShadow(false)
            description.isEnabled = false
            setPinchZoom(false)
            setDrawGridBackground(false)
            setFitBars(true)
            animateY(1000)
            setVisibleXRangeMinimum(20f)
            setDrawValueAboveBar(false)
            setDrawMarkers(false)
            axisLeft.apply {
                setDrawAxisLine(false)
                setDrawGridLines(false)
                setDrawLabels(false)
                setDrawGridLinesBehindData(false)
                invalidate()
            }
            axisRight.apply {
                setDrawAxisLine(false)
                setDrawGridLines(false)
                setDrawLabels(false)
                setDrawGridLinesBehindData(false)
                invalidate()
            }
            xAxis.apply {
                position = XAxis.XAxisPosition.BOTTOM
                setDrawAxisLine(false)
                setDrawGridLines(false)
                axisMinimum = 0f
                setDrawLabels(true)
                valueFormatter = object: IndexAxisValueFormatter() {
                    override fun getFormattedValue(value: Float): String {
                        val remain = value % 6f
                        return if(remain==0f) categorizedSums.getOrNull((value/6f).toInt()-1)?.title ?: "" else ""
                    }
                }
                invalidate()
            }

        }
        chart.data.notifyDataChanged()
        chart.notifyDataSetChanged()
        chart.invalidate()
    }

    @AfterPropsSet
    fun postWorks(){
        sums(categorizedSums)
    }

}