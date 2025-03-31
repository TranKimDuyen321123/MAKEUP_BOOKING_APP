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
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
            mailSender.send(message);
        } catch (Exception e) {
            System.err.println("Error sending email: " + e.getMessage());
        }
    }

    public void sendBookingConfirmation(String to, String bookingDetails) {
        String subject = "Xác nhận đặt lịch Makeup";
        String body = String.format("Chào bạn,\n\nLịch hẹn của bạn đã được xác nhận!\n\n%s\n\nCảm ơn bạn đã sử dụng dịch vụ của chúng tôi!", bookingDetails);
        sendEmail(to, subject, body);
    }

    public void sendPaymentStatus(String to, String status) {
        String subject = "Cập nhật trạng thái thanh toán";
        String body = String.format("Chào bạn,\n\nTrạng thái thanh toán của bạn: %s\n\nCảm ơn bạn đã sử dụng dịch vụ!", status);
        sendEmail(to, subject, body);
    }
}
