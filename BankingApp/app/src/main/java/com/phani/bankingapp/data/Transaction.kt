package com.phani.bankingapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaction_table")
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 6,
    val fromUser: String,
    val toUser: String,
    val amountOfTransaction: Int,
    val statusOfTransaction: String
)