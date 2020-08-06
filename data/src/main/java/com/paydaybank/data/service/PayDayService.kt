package com.paydaybank.data.service

import com.paydaybank.data.model.Customer
import com.paydaybank.data.repository.user.model.InputSignIn
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PayDayService {

    @POST("authenticate")
    suspend fun authenticate(@Body input: InputSignIn):Customer?

    @GET("customers/{id}")
    suspend fun getCustomer(@Path("id") id: Int):Customer?

}