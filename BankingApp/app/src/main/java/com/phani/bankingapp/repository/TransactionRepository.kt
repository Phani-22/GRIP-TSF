package com.phani.bankingapp.repository

import com.phani.bankingapp.data.Transaction
import com.phani.bankingapp.data.TransactionDao

class TransactionRepository(private val transactionDao: TransactionDao) {

    val allTransactions = transactionDao.getAllTransactions()

    suspend fun addTransaction(transaction: Transaction) {
        transactionDao.addTransaction(transaction)
    }
}