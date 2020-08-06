package com.paydaybank.data.offline.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.paydaybank.data.model.Customer

@Dao
abstract class CustomerDao {

    @Query("SELECT * FROM customer limit 1")
    abstract suspend fun getCustomer(): Customer?

    @Insert
    abstract suspend fun insertCustomer(vararg customer: Customer)

    @Query("DELETE FROM customer")
    abstract suspend fun deleteCustomer()

    @Transaction
    @Insert
    suspend fun updateCustomer(customer: Customer){
        deleteCustomer()
        insertCustomer(customer)
    }


}