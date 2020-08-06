package com.paydaybank.data.service

import com.paydaybank.data.model.Customer
import com.paydaybank.data.repository.user.model.InputSignIn
import retrofit2.http.Body
import retrofit2.http.POST

interface PayDayService {

    @POST("authenticate")
    suspend fun authenticate(@Body input: InputSignIn):Customer?

}