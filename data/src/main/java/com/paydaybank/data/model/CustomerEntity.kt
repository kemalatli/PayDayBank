package com.paydaybank.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "customer")
data class CustomerEntity(
    @SerializedName("id")
    @PrimaryKey
    val id:Int = 0,
    @SerializedName("First Name")
    val firstName:String = "",
    @SerializedName("Last Name")
    val lastName:String = "",
    val gender:String = "",
    val email:String = "",
    val dob: Date = Date(),
    val phone:String = ""
)