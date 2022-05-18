package com.phani.bankingapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.phani.bankingapp.data.Transaction
import com.phani.bankingapp.database.TransactionDatabase
import com.phani.bankingapp.database.UserDatabase
import com.phani.bankingapp.repository.TransactionRepository
import com.phani.bankingapp.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TransactionViewModel(application: Application) : AndroidViewModel(application) {
    val allTransactions: LiveData<List<Transaction>>
    private val transactionRepository: TransactionRepository

    init {
        val transactionDao =
            TransactionDatabase.getTransactionDatabaseInstance(application).getTransactionDao()
        transactionRepository = TransactionRepository(transactionDao)
        allTransactions = transactionRepository.allTransactions
    }

    fun addTransaction(transaction: Transaction) {
        viewModelScope.launch(Dispatchers.IO) {
            transactionRepository.addTransaction(transaction)
        }
    }
}