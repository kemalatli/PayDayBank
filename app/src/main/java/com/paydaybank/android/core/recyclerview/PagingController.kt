package com.paydaybank.android.core.recyclerview

import com.airbnb.epoxy.EpoxyController
import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController

open class PagingController<T>(
    private val buildItemCallback: EpoxyController.(currentPosition: Int, item: T?) -> EpoxyModel<*>,
    val buildModelsCallback: PagingController<T>.(List<EpoxyModel<*>>) -> Unit = {}
) : PagedListEpoxyController<T>() {

    override fun buildItemModel(currentPosition: Int, item: T?): EpoxyModel<*> {
        return buildItemCallback.invoke(this, currentPosition, item)
    }

    override fun addModels(models: List<EpoxyModel<*>>) {
        buildModelsCallback(models)
    }

    fun insertItemModels(models: List<EpoxyModel<*>>){
        super.addModels(models)
    }

}