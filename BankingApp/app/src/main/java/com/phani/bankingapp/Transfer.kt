package com.phani.bankingapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.phani.bankingapp.adapter.TransferAdapter
import com.phani.bankingapp.data.Transaction
import com.phani.bankingapp.data.User
import com.phani.bankingapp.databinding.ActivityTransferBinding
import com.phani.bankingapp.viewModel.TransactionViewModel
import com.phani.bankingapp.viewModel.UserViewModel

class Transfer : AppCompatActivity() {

    private lateinit var mBinding: ActivityTransferBinding
    private lateinit var mUserViewModel: UserViewModel
    private lateinit var mTransactionViewModel: TransactionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityTransferBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        mTransactionViewModel = ViewModelProvider(this)[TransactionViewModel::class.java]

        val curUser = intent.getParcelableExtra<User>("fromUser") ?: return
        val allUsers = mUserViewModel.allUsers.value ?: emptyList()

        val recyclerView = mBinding.transferRecyclerView
        val adapter = TransferAdapter(curUser)
        adapter.setAllUsersList(allUsers)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        mUserViewModel.allUsers.observe(this) {
            val curList = mutableListOf<User>()
            for (user in it) {
                if (user.id != curUser.id) curList.add(user)
            }
            adapter.setAllUsersList(curList)
        }
    }
}