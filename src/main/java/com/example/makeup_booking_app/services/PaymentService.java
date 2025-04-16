package com.example.makeup_booking_app.services;

import com.example.makeup_booking_app.models.Payment;
import com.example.makeup_booking_app.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Optional<Payment> getPaymentById(Long id) {
        return paymentRepository.findById(id);
    }

    public Payment createPayment(Payment payment) {
        if (payment.getCreatedAt() == null) {
            payment.setCreatedAt(Instant.now());
        }
        if (payment.getPaymentStatus() == null) {
            payment.setPaymentStatus("PENDING");
        }
        return paymentRepository.save(payment);
    }

    public Payment updatePayment(Long id, Payment paymentDetails) {
        return paymentRepository.findById(id).map(payment -> {
            payment.setAmount(paymentDetails.getAmount());
            payment.setPaymentMethod(paymentDetails.getPaymentMethod());
            payment.setTransactionId(paymentDetails.getTransactionId());
            payment.setPaymentStatus(paymentDetails.getPaymentStatus());
            return paymentRepository.save(payment);
        }).orElseThrow(() -> new RuntimeException("Payment not found with id: " + id));
    }

    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }
    public BigDecimal getRevenueByDate(LocalDate date) {
        // Tính toán startOfDay và endOfDay từ LocalDate
        Instant startOfDay = date.atStartOfDay(ZoneOffset.UTC).toInstant();
        Instant endOfDay = date.plusDays(1).atStartOfDay(ZoneOffset.UTC).toInstant();

        // Gọi repository để lấy doanh thu theo ngày
        return paymentRepository.getRevenueByDate(startOfDay, endOfDay);
    }
}
