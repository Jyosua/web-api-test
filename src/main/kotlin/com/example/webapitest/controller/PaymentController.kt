package com.example.webapitest.controller

import com.example.webapitest.model.Payment
import com.example.webapitest.model.dto.CreatePaymentRequest
import com.example.webapitest.service.PaymentService
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping("/payments")
class PaymentController(private val paymentService: PaymentService) {

    @GetMapping
    fun getTotalAmount(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) startDate: LocalDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) endDate: LocalDate
    ): Int {
        return paymentService.getTotalByDateRange(startDate, endDate)
    }

    @PostMapping
    fun addPayment(@RequestBody request: CreatePaymentRequest): ResponseEntity<Payment> {
        val payment = paymentService.addPayment(request)
        return ResponseEntity(payment, HttpStatus.CREATED)
    }
}