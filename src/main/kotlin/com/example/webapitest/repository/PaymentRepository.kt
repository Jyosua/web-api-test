package com.example.webapitest.repository

import com.example.webapitest.model.BankAccount
import com.example.webapitest.model.Payment
import com.example.webapitest.model.dto.CreatePaymentRequest
import com.example.webapitest.repository.db.PaymentBankAccountEntity
import com.example.webapitest.repository.db.PaymentEntity
import com.example.webapitest.repository.db.PaymentTable
import com.example.webapitest.repository.db.toPayment
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
class PaymentRepository {
    fun getBy(startDate: LocalDate, endDate: LocalDate): Sequence<Payment> {
        return transaction {
            PaymentEntity.find {
                (PaymentTable.transferDate greaterEq startDate) and
                (PaymentTable.transferDate lessEq endDate)
            }
            .asSequence()
            .map { it.toPayment() }
        }
    }

    fun insertPayment(payment: Payment, bankAccount: BankAccount): Payment {
        return transaction {
            val newPayment = PaymentEntity.new {
                userId = payment.userId
                amount = payment.amount
                fee = payment.fee
                feeRate = payment.feeRate
                taxRate = payment.taxRate
                billingAmount = payment.billingAmount
                transferDate = payment.transferDate
                uploadedDate = payment.uploadedDate
                status = payment.status
            }

            PaymentBankAccountEntity.new {
                paymentId = newPayment.id
                bankCode = bankAccount.bankCode
                bankName = bankAccount.bankName
                branchCode = bankAccount.branchCode
                branchName = bankAccount.branchName
                accountType = bankAccount.accountType
                accountNumber = bankAccount.accountNumber
                accountHolder = bankAccount.accountHolder
            }

            newPayment.toPayment()
        }
    }
}