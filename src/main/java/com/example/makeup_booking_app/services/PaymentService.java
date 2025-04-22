package com.example.makeup_booking_app.services;

import com.example.makeup_booking_app.dtos.PaymentDTO;
import com.example.makeup_booking_app.models.Booking;
import com.example.makeup_booking_app.models.Payment;
import com.example.makeup_booking_app.repositories.BookingRepository;
import com.example.makeup_booking_app.repositories.PaymentRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;

    public PaymentService(PaymentRepository paymentRepository, BookingRepository bookingRepository) {
        this.paymentRepository = paymentRepository;
        this.bookingRepository = bookingRepository;
    }

    public List<PaymentDTO> getAllPayments() {
        return paymentRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<PaymentDTO> getPaymentById(Long id) {
        return paymentRepository.findById(id).map(this::toDTO);
    }

    public PaymentDTO createPayment(PaymentDTO dto) {
        Payment payment = toEntity(dto);

        if (payment.getCreatedAt() == null) {
            payment.setCreatedAt(Instant.now());
        }
        if (payment.getPaymentStatus() == null) {
            payment.setPaymentStatus("PENDING");
        }

        Payment saved = paymentRepository.save(payment);
        return toDTO(saved);
    }

    public PaymentDTO updatePayment(Long id, PaymentDTO dto) {
        Payment existing = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found with id: " + id));

        existing.setAmount(dto.getAmount());
        existing.setPaymentMethod(dto.getPaymentMethod());
        existing.setTransactionId(dto.getTransactionId());
        existing.setPaymentStatus(dto.getPaymentStatus());

        return toDTO(paymentRepository.save(existing));
    }

    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }

    public BigDecimal getRevenueByDate(LocalDate date) {
        Instant startOfDay = date.atStartOfDay(ZoneOffset.UTC).toInstant();
        Instant endOfDay = date.plusDays(1).atStartOfDay(ZoneOffset.UTC).toInstant();

        return paymentRepository.getRevenueByDate(startOfDay, endOfDay);
    }

    // Mapping helpers
    private PaymentDTO toDTO(Payment payment) {
        return new PaymentDTO(
                payment.getId(),
                payment.getBooking().getId(),
                payment.getAmount(),
                payment.getPaymentMethod(),
                payment.getTransactionId(),
                payment.getPaymentStatus(),
                payment.getCreatedAt()
        );
    }

    private Payment toEntity(PaymentDTO dto) {
        Booking booking = bookingRepository.findById(dto.getBookingId())
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + dto.getBookingId()));

        Payment payment = new Payment();
        payment.setId(dto.getId()); // optional, only for update
        payment.setBooking(booking);
        payment.setAmount(dto.getAmount());
        payment.setPaymentMethod(dto.getPaymentMethod());
        payment.setTransactionId(dto.getTransactionId());
        payment.setPaymentStatus(dto.getPaymentStatus());
        payment.setCreatedAt(dto.getCreatedAt());

        return payment;
    }
}
