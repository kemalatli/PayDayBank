package com.paydaybank.data.offline

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.paydaybank.data.model.AccountEntity
import com.paydaybank.data.model.CustomerEntity
import com.paydaybank.data.model.TransactionEntity
import com.paydaybank.data.offline.converters.Converters
import com.paydaybank.data.offline.dao.AccountDao
import com.paydaybank.data.offline.dao.CustomerDao
import com.paydaybank.data.offline.dao.TransactionDao

@Database(entities = [CustomerEntity::class, AccountEntity::class, TransactionEntity::class], version = 7)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun customerDao(): CustomerDao
    abstract fun accountDao(): AccountDao
    abstract fun transactionDao(): TransactionDao

}