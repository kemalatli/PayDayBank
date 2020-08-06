package com.paydaybank.android.models

import android.content.Context
import android.util.AttributeSet
import com.airbnb.epoxy.AfterPropsSet
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.*
import com.google.android.material.card.MaterialCardView
import com.paydaybank.android.R
import com.paydaybank.data.repository.transaction.model.CategorizedSum

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class ModelViewPieChart @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : MaterialCardView(context, attrs, defStyleAttr) {

    private var categorizedSums:List<CategorizedSum> = listOf()

    private var chartLabel:String = ""

    private val chart:PieChart


    init {
        // Inflate
        inflate(context, R.layout.model_view_pie_chart, this)
        // Set views
        chart = findViewById(R.id.chart)
    }

    @ModelProp
    fun sums(categorizedSums:List<CategorizedSum>){
        this.categorizedSums = categorizedSums
    }

    @TextProp
    fun chartLabel(label:CharSequence?){
        this.chartLabel = chartLabel.toString()
    }

    @AfterPropsSet
    fun postBindSetup(){
        val dataSet = PieDataSet(
            categorizedSums.filter { it.sum<0.0 }.mapIndexed{  _, item ->
                PieEntry(item.sum.toFloat())
            },
            chartLabel
        )
        chart.data = PieData(dataSet)
        chart.invalidate()
    }

}