package com.phani.bankingapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.phani.bankingapp.adapter.UserAdapter
import com.phani.bankingapp.databinding.ActivityMainBinding
import com.phani.bankingapp.viewModel.UserViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        val recyclerView = mBinding.recyclerView
        val adapter = UserAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // observing live data
        mUserViewModel.allUsers.observe(this) {
            adapter.setAllUsersList(it)
        }

        // viewing user on click
        mBinding.transactionsHistoryBtn.setOnClickListener {
            startActivity(Intent(this, Transactions::class.java))
        }
    }
}