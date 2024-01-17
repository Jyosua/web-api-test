package com.example.webapitest.model

data class BankAccount(
    val bankCode: String,
    val bankName: String,
    val branchCode: String,
    val branchName: String,
    val accountType: Int,
    val accountNumber: String,
    val accountHolder: String,
)