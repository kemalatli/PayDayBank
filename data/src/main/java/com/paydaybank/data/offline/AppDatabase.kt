package com.paydaybank.data.offline

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.paydaybank.data.model.Account
import com.paydaybank.data.model.Customer
import com.paydaybank.data.offline.converters.Converters
import com.paydaybank.data.offline.dao.AccountDao
import com.paydaybank.data.offline.dao.CustomerDao

@Database(entities = [Customer::class, Account::class], version = 3)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun customerDao(): CustomerDao
    abstract fun accountDao(): AccountDao
    abstract fun transactionDao(): AccountDao

}