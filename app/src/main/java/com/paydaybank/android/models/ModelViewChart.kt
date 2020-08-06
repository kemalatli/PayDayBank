package com.paydaybank.android.models

import android.content.Context
import android.util.AttributeSet
import com.airbnb.epoxy.AfterPropsSet
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.google.android.material.card.MaterialCardView
import com.paydaybank.android.R
import com.paydaybank.data.repository.transaction.model.CategorizedSum

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class ModelViewChart @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : MaterialCardView(context, attrs, defStyleAttr) {

    private var categorizedSums:List<CategorizedSum> = listOf()

    private val chart:BarChart


    init {
        // Inflate
        inflate(context, R.layout.model_view_chart, this)
        // Set views
        chart = findViewById(R.id.chart)
    }

    @ModelProp
    fun sums(categorizedSums:List<CategorizedSum>){
        this.categorizedSums = categorizedSums
    }

    @AfterPropsSet
    fun postBindSetup(){

        val dataSet = BarDataSet(
            categorizedSums.mapIndexed { index, categorizedSum ->
                BarEntry(index.toFloat(), categorizedSum.sum.toFloat())
            },
            "Summary"
        )
        chart.data = BarData(dataSet)
        chart.invalidate()

    }

}