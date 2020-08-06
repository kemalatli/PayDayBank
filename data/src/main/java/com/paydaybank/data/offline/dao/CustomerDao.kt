package com.paydaybank.data.offline.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.paydaybank.data.model.CustomerEntity

@Dao
abstract class CustomerDao {

    @Query("SELECT * FROM customer limit 1")
    abstract suspend fun getCustomer(): CustomerEntity?

    @Insert
    abstract suspend fun insertCustomer(vararg customer: CustomerEntity)

    @Query("DELETE FROM customer")
    abstract suspend fun deleteCustomer()

    @Transaction
    open suspend fun updateCustomer(customer: CustomerEntity){
        deleteCustomer()
        insertCustomer(customer)
    }


}