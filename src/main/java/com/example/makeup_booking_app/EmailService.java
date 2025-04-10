package com.example.makeup_booking_app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String toEmail, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom("your_email@example.com"); // thay bằng email thật nếu cần

        mailSender.send(message);
    }

    public void sendBookingConfirmation(String toEmail, String bookingDetails) {
        sendEmail(toEmail, "Xác nhận đặt lịch", "Thông tin đặt lịch: " + bookingDetails);
    }

    public void sendPaymentStatus(String toEmail, String status) {
        sendEmail(toEmail, "Trạng thái thanh toán", "Trạng thái mới: " + status);
    }
}
