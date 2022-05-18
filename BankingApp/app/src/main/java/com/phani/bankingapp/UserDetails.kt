package com.phani.bankingapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.phani.bankingapp.data.User
import com.phani.bankingapp.databinding.ActivityUserDetailsBinding

class UserDetails : AppCompatActivity() {

    private lateinit var mBinding: ActivityUserDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityUserDetailsBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        val curUser = intent.getParcelableExtra<User>("currentUser")

        // setting user details
        if (curUser != null) {
            mBinding.userNameTVDetails.text = curUser.userName
            mBinding.emailTVDetails.text = curUser.emailId
            mBinding.phoneNumberTVDetails.text = curUser.phoneNumber
            mBinding.balanceTVDetails.text = curUser.currentBalance.toString()
        }

        mBinding.transferBtn.setOnClickListener {
            if (curUser != null) {
                startTransfer(curUser)
            }
        }
    }

    // starting transfer from user clicked
    private fun startTransfer(curUser: User) {
        val intent = Intent(this, Transfer::class.java)
        intent.putExtra("fromUser", curUser)
        startActivity(intent)
        finish()
    }
}