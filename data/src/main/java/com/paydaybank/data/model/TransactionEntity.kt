package com.paydaybank.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "transactions")
data class TransactionEntity(
    @SerializedName("id")
    @PrimaryKey
    val id:Int = 0,
    @SerializedName("account_id")
    val account_id:Int = 0,
    val amount:Double = 0.0,
    val vendor:String = "",
    val category:String = "",
    val date: Date = Date(),
    val month:String = "",
    val time:String = ""
)