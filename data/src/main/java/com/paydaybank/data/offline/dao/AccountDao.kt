package com.paydaybank.data.offline.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.paydaybank.data.model.Account
import com.paydaybank.data.model.Customer

@Dao
abstract class AccountDao {

    @Query("SELECT * FROM accounts")
    abstract suspend fun getAccounts(): List<Account>

    @Insert
    abstract suspend fun insertAccount(accounts:List<Account>)

    @Query("DELETE FROM accounts")
    abstract suspend fun deleteAccounts()

    @Transaction
    @Insert
    suspend fun updateAccounts(accounts:List<Account>){
        deleteAccounts()
        insertAccount(accounts)
    }


}