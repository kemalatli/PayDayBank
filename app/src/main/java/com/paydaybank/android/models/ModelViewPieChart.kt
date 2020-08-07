package com.paydaybank.android.models

import android.content.Context
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.airbnb.epoxy.AfterPropsSet
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.google.android.material.card.MaterialCardView
import com.paydaybank.android.R
import com.paydaybank.android.models.helper.ChartColorManager
import com.paydaybank.data.repository.transaction.model.CategorizedSum
import kotlin.math.abs

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class ModelViewPieChart @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : MaterialCardView(context, attrs, defStyleAttr) {


    private val chart:PieChart

    private var categorizedSums:List<CategorizedSum> = listOf()

    init {
        // Inflate
        inflate(context, R.layout.model_view_pie_chart, this)
        // Set views
        chart = findViewById(R.id.chart)
    }

    @ModelProp
    fun sums(categorizedSums:List<CategorizedSum>){
        this.categorizedSums = categorizedSums
        val filtered = categorizedSums.filter { it.sum<0.0 }
        val dataSet = PieDataSet(
            filtered.mapIndexed{  _, item ->
                PieEntry(abs(item.sum.toFloat()), item.title)
            },
            ""
        ).apply {
            setColors(
                ChartColorManager.getColors(filtered).toIntArray(),
                context
            )
        }
        chart.apply {
            data = PieData(dataSet).apply {
                setDrawValues(false)
                setDrawMarkers(false)
            }
            legend.isEnabled = false
            description.isEnabled = false
            animateY(1000)
            setDrawMarkers(false)
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