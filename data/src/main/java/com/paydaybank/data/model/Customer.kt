package com.paydaybank.data.model

import com.google.gson.annotations.SerializedName

data class Customer(
    @SerializedName("id")
    val id:Int = 0,
    val firstName:String = "",
    val lastName:String = "",
    val gender:String = "",
    val email:String = "",
    val password:String = "",
    val dob:String = "",
    val phone:String = ""
)