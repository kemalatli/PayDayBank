package com.paydaybank.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "accounts")
data class AccountEntity(
    @SerializedName("id")
    @PrimaryKey
    val id:Int = 0,
    @SerializedName("customer_id")
    val customerId:Int = 0,
    val iban:String = "",
    val type:String = "",
    @SerializedName("date_created")
    val dateCreated:String = "",
    val active:Boolean = true
)