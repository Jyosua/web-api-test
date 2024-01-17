package com.example.webapitest.model.dto

data class CreatePaymentRequest(
        val userId: Int,
        val amount: Int,
        val bankCode: String,
        val bankName: String,
        val branchCode: String,
        val branchName: String,
        val accountType: Int,
        val accountNumber: String,
        val accountHolder: String,
)