package com.example.makeup_booking_app.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "appointment_id", nullable = false)
    private Appointment appointment;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false)
    private PaymentMethod paymentMethod;

    @Column(name = "transaction_id", nullable = false, unique = true)
    private String transactionId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private PaymentStatus status = PaymentStatus.PENDING;

    @Column(name = "created_at", nullable = false, insertable = false, updatable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false,
            insertable = false, updatable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Timestamp updatedAt;

    public Payment() {}

    public Payment(Long id, Appointment appointment, User user, BigDecimal amount, com.example.makeup_booking_app.model.Payment.PaymentMethod paymentMethod, String transactionId, com.example.makeup_booking_app.model.Payment.PaymentStatus status) {
        this.id = id;
        this.appointment = appointment;
        this.user = user;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.transactionId = transactionId;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", appointment=" + appointment +
                ", user=" + user +
                ", amount=" + amount +
                ", paymentMethod=" + paymentMethod +
                ", transactionId='" + transactionId + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {this.id = id;}

    public Appointment getAppointment() {return appointment;}

    public void setAppointment(Appointment appointment) {this.appointment = appointment;}

    public User getUser() {return user;}

    public void setUser(User user) {this.user = user;}

    public BigDecimal getAmount() {return amount;}

    public void setAmount(BigDecimal amount) {this.amount = amount;}

    public PaymentMethod getPaymentMethod() {return paymentMethod;}

    public void setPaymentMethod(PaymentMethod paymentMethod) {this.paymentMethod = paymentMethod;}

    public String getTransactionId() {return transactionId;}

    public void setTransactionId(String transactionId) {this.transactionId = transactionId;}

    public PaymentStatus getStatus() {return status;}

    public void setStatus(PaymentStatus status) {this.status = status;}

    public Timestamp getCreatedAt() {return createdAt;}

    public Timestamp getUpdatedAt() {return updatedAt;}

    public enum PaymentMethod {CASH, CREDIT_CARD, MOMO}

    public enum PaymentStatus {PENDING, COMPLETED, FAILED}
}
