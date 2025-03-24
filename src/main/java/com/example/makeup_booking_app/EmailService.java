package com.example.makeupbookingapp.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }

    // Gửi email xác nhận đặt lịch
    public void sendBookingConfirmation(String to, String bookingDetails) {
        String subject = "Xác nhận đặt lịch Makeup";
        String body = "Chào bạn,\n\nLịch hẹn của bạn đã được xác nhận!\n\n" + bookingDetails + "\n\nCảm ơn bạn đã sử dụng dịch vụ của chúng tôi!";
        sendEmail(to, subject, body);
    }

    // Gửi email trạng thái thanh toán
    public void sendPaymentStatus(String to, String status) {
        String subject = "Cập nhật trạng thái thanh toán";
        String body = "Chào bạn,\n\nTrạng thái thanh toán của bạn: " + status + "\n\nCảm ơn bạn đã sử dụng dịch vụ!";
        sendEmail(to, subject, body);
    }
}
