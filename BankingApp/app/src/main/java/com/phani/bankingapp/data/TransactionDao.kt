package com.phani.bankingapp.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTransaction(transaction: Transaction)

    @Query("SELECT * FROM transaction_table ORDER BY id DESC")
    fun getAllTransactions(): LiveData<List<Transaction>>
}