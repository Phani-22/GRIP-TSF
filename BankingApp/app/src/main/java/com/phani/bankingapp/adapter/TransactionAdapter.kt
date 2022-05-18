package com.phani.bankingapp.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.phani.bankingapp.R
import com.phani.bankingapp.data.Transaction

class TransactionAdapter : RecyclerView.Adapter<TransactionAdapter.TransactionHolder>() {

    var allTransactions = emptyList<Transaction>()

    class TransactionHolder(view: View) : RecyclerView.ViewHolder(view) {
        val usersOfTransaction: TextView = view.findViewById(R.id.fromUserToUserTV)
        val amountOfTransaction: TextView = view.findViewById(R.id.amountTV)
        val transactionId: TextView = view.findViewById(R.id.transactionId)
        val successOrFailureTV: TextView = view.findViewById(R.id.isSuccessTV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionHolder {
        return TransactionHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.transaction_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TransactionHolder, position: Int) {
        val curTransaction = allTransactions[position]
        var text = "${curTransaction.fromUser} -> ${curTransaction.toUser}"
        holder.usersOfTransaction.text = text
        text = "Amount : ${curTransaction.amountOfTransaction}"
        holder.amountOfTransaction.text = text
        text = "TID :  ${curTransaction.id}"
        holder.transactionId.text = text
        holder.successOrFailureTV.text = curTransaction.statusOfTransaction
        val color = if (curTransaction.statusOfTransaction == "SUCCESS") {
            Color.GREEN
        } else {
            Color.RED
        }
        holder.successOrFailureTV.setTextColor(color)
    }

    override fun getItemCount(): Int {
        return allTransactions.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setAllTransactionsList(transactions: List<Transaction>) {
        allTransactions = transactions
        notifyDataSetChanged()
    }
}