package com.phani.bankingapp.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.phani.bankingapp.ConfirmTransfer
import com.phani.bankingapp.R
import com.phani.bankingapp.data.User


class TransferAdapter(private val fromUser: User) : RecyclerView.Adapter<TransferAdapter.TransferHolder>() {

    var allUsers = emptyList<User>()

    class TransferHolder(view: View) : RecyclerView.ViewHolder(view) {
        val userNameTV: TextView = view.findViewById(R.id.userNameTV)
        val balanceTV: TextView = view.findViewById(R.id.balanceTV)
        val userItem: ConstraintLayout = view.findViewById(R.id.userItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransferHolder {
        return TransferHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TransferHolder, position: Int) {
        val curUser = allUsers[position]

        holder.userNameTV.text = curUser.userName
        val text = "${curUser.currentBalance}"
        holder.balanceTV.text = text

        holder.userItem.setOnClickListener {
            val intent = Intent(holder.itemView.context, ConfirmTransfer::class.java)
            intent.putExtra("fromUser", fromUser)
            intent.putExtra("toUser", curUser)
            holder.itemView.context.startActivity(intent)
            (holder.itemView.context as Activity).finish()
        }
    }

    override fun getItemCount(): Int {
        return allUsers.size

    }

    @SuppressLint("NotifyDataSetChanged")
    fun setAllUsersList(users: List<User>) {
        allUsers = users
        notifyDataSetChanged()
    }
}