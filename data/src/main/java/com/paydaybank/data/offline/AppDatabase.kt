package com.paydaybank.data.offline

import androidx.room.Database
import androidx.room.RoomDatabase
import com.paydaybank.data.model.Customer
import com.paydaybank.data.offline.dao.CustomerDao

@Database(entities = arrayOf(Customer::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun customerDao(): CustomerDao

}