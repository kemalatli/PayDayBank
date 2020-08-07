package com.paydaybank.android.models.helper

import android.util.SparseArray
import com.paydaybank.android.R
import com.paydaybank.data.repository.transaction.model.CategorizedSum

object ChartColorManager {

    private val map = hashMapOf<String, Int>()

    var currentIndex = 0

    private val colors = listOf(
        R.color.chart_color1,
        R.color.chart_color2,
        R.color.chart_color3,
        R.color.chart_color4,
        R.color.chart_color5,
        R.color.chart_color6,
        R.color.chart_color7,
        R.color.chart_color8,
        R.color.chart_color9
    )

    fun getColors(data:List<CategorizedSum>): ArrayList<Int> {
        val list = arrayListOf<Int>()
        val titles = data.map { it.title }.distinct()
        titles.forEach {
            if(map.containsKey(it)) {
                map[it]?.let { clr -> list.add(clr) }
            }else{
                val index = currentIndex % colors.size
                val clr = colors[index]
                list.add(clr)
                map[it] = clr
                currentIndex++
            }
        }
        return list
    }

}