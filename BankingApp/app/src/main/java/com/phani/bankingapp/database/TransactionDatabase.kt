package com.phani.bankingapp.database

import android.content.Context
import androidx.room.*
import com.phani.bankingapp.data.Transaction
import com.phani.bankingapp.data.TransactionDao

@Database(entities = [Transaction::class], version = 1, exportSchema = false)
abstract class TransactionDatabase : RoomDatabase() {

    abstract fun getTransactionDao(): TransactionDao

    companion object {
        @Volatile
        private var INSTANCE: TransactionDatabase? = null

        fun getTransactionDatabaseInstance(context: Context): TransactionDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    TransactionDatabase::class.java,
                    "transaction_database"
                ).createFromAsset("database/transactions.db").build()
                INSTANCE = instance
                return instance
            }
        }
    }
}