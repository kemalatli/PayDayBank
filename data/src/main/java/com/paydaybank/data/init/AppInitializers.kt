package com.paydaybank.data.init

import javax.inject.Inject

class AppInitializers @Inject constructor(
    private val initializers: Set<@JvmSuppressWildcards AppInitializer>
){
    fun init() {
        initializers.forEach {
            it.initialize()
        }
    }
}