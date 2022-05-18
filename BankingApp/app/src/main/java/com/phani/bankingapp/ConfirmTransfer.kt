package com.phani.bankingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.phani.bankingapp.data.Transaction
import com.phani.bankingapp.data.User
import com.phani.bankingapp.databinding.ActivityConfirmTransferBinding
import com.phani.bankingapp.viewModel.TransactionViewModel
import com.phani.bankingapp.viewModel.UserViewModel

class ConfirmTransfer : AppCompatActivity() {

    private lateinit var mBinding: ActivityConfirmTransferBinding
    private lateinit var mUserViewModel: UserViewModel
    private lateinit var mTransactionViewModel: TransactionViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityConfirmTransferBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mUserViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        mTransactionViewModel = ViewModelProvider(this)[TransactionViewModel::class.java]

        val fromUser = intent.getParcelableExtra<User>("fromUser") ?: return
        val toUser = intent.getParcelableExtra<User>("toUser") ?: return

        mBinding.fromName.text = fromUser.userName
        val fromBal = "Balance :  ${fromUser.currentBalance}"
        mBinding.fromCustomerBalance.text = fromBal
        mBinding.toName.text = toUser.userName

        mBinding.cancelTransactionBtn.setOnClickListener {
            onBackPressed()
        }

        mBinding.proceedToTransact.setOnClickListener {
            val text = mBinding.editText.text.toString()
            if (text.isEmpty()) {
                Toast.makeText(this, "Please Enter amount!!", Toast.LENGTH_SHORT).show()
            }

            val amount = Integer.parseInt(text)
            val balanceOfSender = fromUser.currentBalance
            // checking entered amount and available balance
            if (amount > balanceOfSender) {
                Toast.makeText(this, "Insufficient Balance", Toast.LENGTH_LONG).show()
                mTransactionViewModel.addTransaction(
                    Transaction(
                        fromUser = fromUser.userName,
                        toUser = toUser.userName,
                        amountOfTransaction = amount,
                        statusOfTransaction = "FAILURE"
                    )
                )
            } else {
                val modifiedFromUser = User(
                    fromUser.id,
                    fromUser.userName,
                    fromUser.phoneNumber,
                    fromUser.currentBalance - amount,
                    fromUser.emailId
                )
                val modifiedToUser = User(
                    toUser.id,
                    toUser.userName,
                    toUser.phoneNumber,
                    toUser.currentBalance + amount,
                    toUser.emailId
                )
                // removing money from user and adding it to other user
                mUserViewModel.transferMoney(modifiedFromUser)
                mUserViewModel.transferMoney(modifiedToUser)
                // store it in transactions
                mTransactionViewModel.addTransaction(
                    Transaction(
                        fromUser = fromUser.userName,
                        toUser = toUser.userName,
                        amountOfTransaction = amount,
                        statusOfTransaction = "SUCCESS"
                    )
                )
            }
            finish()
        }
    }
}
