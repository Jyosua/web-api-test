package com.example.webapitest.repository.db

import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

object PaymentBankAccountsTable : LongIdTableBase("payment_bank_accounts") {
    val paymentId = reference("payment_id", PaymentTable)
    val bankCode = varchar("bank_code", 4)
    val bankName = varchar("bank_name", 64)
    val branchCode = varchar("branch_code", 3)
    val branchName = varchar("branch_name", 64)
    val accountType = integer("account_type")
    val accountNumber = varchar("account_number", 8)
    val accountHolder = varchar("account_holder", 128)
}

class PaymentBankAccountEntity(id: EntityID<Long>) : LongEntityBase(id) {
    companion object : LongEntityClass<PaymentBankAccountEntity>(PaymentBankAccountsTable)

    var paymentId by PaymentBankAccountsTable.paymentId
    var bankCode by PaymentBankAccountsTable.bankCode
    var bankName by PaymentBankAccountsTable.bankName
    var branchCode by PaymentBankAccountsTable.branchCode
    var branchName by PaymentBankAccountsTable.branchName
    var accountType by PaymentBankAccountsTable.accountType
    var accountNumber by PaymentBankAccountsTable.accountNumber
    var accountHolder by PaymentBankAccountsTable.accountHolder
    override var createdAt by PaymentBankAccountsTable.createdAt
    override var updatedAt by PaymentBankAccountsTable.updatedAt
}