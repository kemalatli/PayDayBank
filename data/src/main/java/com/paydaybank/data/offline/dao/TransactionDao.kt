package com.paydaybank.data.offline.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.paydaybank.data.model.Account
import com.paydaybank.data.model.Customer
import com.paydaybank.data.model.TransactionEntity

@Dao
abstract class TransactionDao {

    @Query("SELECT * FROM transactions")
    abstract suspend fun getTransactions(): List<TransactionEntity>

    @Insert
    abstract suspend fun insertTransactions(transactions:List<TransactionEntity>)

    @Query("DELETE FROM transactions")
    abstract suspend fun deleteTransactions()

    @Transaction
    @Insert
    suspend fun updateTransactions(transactions:List<TransactionEntity>){
        deleteTransactions()
        insertTransactions(transactions)
    }


}