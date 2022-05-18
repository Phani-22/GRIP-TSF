package com.phani.bankingapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.phani.bankingapp.adapter.TransactionAdapter
import com.phani.bankingapp.databinding.ActivityTransactionsBinding
import com.phani.bankingapp.viewModel.TransactionViewModel

class Transactions : AppCompatActivity() {

    private lateinit var mBinding: ActivityTransactionsBinding
    private lateinit var mTransactionViewModel: TransactionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityTransactionsBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mTransactionViewModel = ViewModelProvider(this)[TransactionViewModel::class.java]

        val recyclerView = mBinding.transactionRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = TransactionAdapter()
        recyclerView.adapter = adapter

        mTransactionViewModel.allTransactions.observe(this) {
            adapter.setAllTransactionsList(it)
        }
    }
}