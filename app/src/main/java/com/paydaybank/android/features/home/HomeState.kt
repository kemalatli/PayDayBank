package com.paydaybank.android.features.home

import com.paydaybank.data.repository.transaction.model.CategorizedSum

data class HomeState(
    val categorySums:List<CategorizedSum> = listOf(),
    val months:List<String> = listOf(),
    val selectedMonth:String = ""
){

    val filteredSums:List<CategorizedSum> get() = categorySums.filter { it.month == selectedMonth }

}