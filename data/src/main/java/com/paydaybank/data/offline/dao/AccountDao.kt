package com.paydaybank.data.offline.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.paydaybank.data.model.AccountEntity

@Dao
abstract class AccountDao {

    @Query("SELECT * FROM accounts")
    abstract suspend fun getAccounts(): List<AccountEntity>

    @Insert
    abstract suspend fun insertAccount(accounts:List<AccountEntity>)

    @Query("DELETE FROM accounts")
    abstract suspend fun deleteAccounts()

    @Transaction
    open suspend fun updateAccounts(accounts:List<AccountEntity>){
        deleteAccounts()
        insertAccount(accounts)
    }


}