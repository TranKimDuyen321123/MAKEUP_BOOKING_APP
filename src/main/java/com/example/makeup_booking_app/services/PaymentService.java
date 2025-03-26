package com.example.makeup_booking_app.services;

import com.example.makeup_booking_app.models.Payment;
import com.example.makeup_booking_app.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment createPayment(Payment payment) {
        payment.setStatus("PENDING");
        payment.setCreatedAt(new Date());
        return paymentRepository.save(payment);
    }

    public List<Payment> getUserPayments(Long userId) {
        return paymentRepository.findByUserIdOrderByCreatedAtDesc(userId);
    }

    public Payment updatePaymentStatus(Long paymentId, String status) {
        return paymentRepository.findById(paymentId).map(payment -> {
            payment.setStatus(status);
            return paymentRepository.save(payment);
        }).orElse(null);
    }

    public List<Payment> getPaymentsByStatus(String status) {
        return paymentRepository.findByStatus(status);
    }
}
