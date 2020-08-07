package com.paydaybank.data.offline.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.paydaybank.data.model.TransactionEntity
import com.paydaybank.data.repository.transaction.model.CategorizedSum
import kotlinx.coroutines.flow.Flow

@Dao
abstract class TransactionDao {

    @Query("SELECT * FROM transactions order by time desc")
    abstract fun getTransactions(): DataSource.Factory<Int, TransactionEntity>

    @Query("SELECT * FROM transactions where month=:month order by time desc")
    abstract fun getTransactionsByMonth(month:String): DataSource.Factory<Int, TransactionEntity>

    @Query("SELECT category as title, month, SUM(amount) as sum FROM transactions group by title, month")
    abstract fun getCategorySums(): Flow<List<CategorizedSum>>

    @Insert
    abstract suspend fun insertTransactions(transactions:List<TransactionEntity>)

    @Query("DELETE FROM transactions")
    abstract suspend fun deleteTransactions()

    @Transaction
    open suspend fun updateTransactions(transactions:List<TransactionEntity>){
        deleteTransactions()
        insertTransactions(transactions)
    }


}