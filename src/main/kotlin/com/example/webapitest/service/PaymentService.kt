package com.example.webapitest.service

import com.example.webapitest.model.BankAccount
import com.example.webapitest.model.Payment
import com.example.webapitest.model.dto.CreatePaymentRequest
import com.example.webapitest.repository.PaymentRepository
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDate
import javax.transaction.Transactional

@Service
class PaymentService(private val paymentRepository: PaymentRepository) {
    private val feeRate = 4 // 4%
    private val taxRate = 10 // 10%
    @Transactional
    fun getTotalByDateRange(startDate: LocalDate, endDate: LocalDate): Int {
        val payments = paymentRepository.getBy(startDate, endDate)
        return payments.sumOf { it.amount }
    }

    @Transactional
    fun addPayment(request: CreatePaymentRequest): Payment {
        val fee = request.amount * (feeRate / 100.0)
        val tax = (fee * (taxRate / 100.0)).toInt()
        val billingAmount = request.amount + fee.toInt() + tax

        val payment = Payment(
                id = null,
                userId = request.userId,
                amount = request.amount,
                fee = fee.toInt(),
                feeRate = BigDecimal(feeRate / 100.0),
                taxRate = taxRate,
                billingAmount = billingAmount,
                transferDate = LocalDate.now(),
                uploadedDate = LocalDate.now(),
                status = 0
        )

        val bankAccount = BankAccount(
                bankCode = request.bankCode,
                bankName = request.bankName,
                branchCode = request.branchCode,
                branchName = request.branchName,
                accountType = request.accountType,
                accountNumber = request.accountNumber,
                accountHolder = request.accountHolder,
        )

        return paymentRepository.insertPayment(payment, bankAccount)
    }
}