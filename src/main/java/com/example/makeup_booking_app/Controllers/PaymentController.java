package com.example.makeup_booking_app.Controllers;

import com.example.makeup_booking_app.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/vnpay/{bookingId}")
    public ResponseEntity<String> createVNPayPayment(@PathVariable Long bookingId, @RequestParam Double amount) {
        String paymentUrl = paymentService.createVNPayPayment(bookingId, amount);
        return ResponseEntity.ok(paymentUrl);
    }
}