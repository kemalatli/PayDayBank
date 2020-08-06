package com.paydaybank.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Customer(
    @SerializedName("id")
    @PrimaryKey
    val id:Int = 0,
    val firstName:String = "",
    val lastName:String = "",
    val gender:String = "",
    val email:String = "",
    val password:String = "",
    val dob:String = "",
    val phone:String = ""
)