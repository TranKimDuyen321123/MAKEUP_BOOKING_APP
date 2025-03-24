package com.example.makeup_booking_app;

import com.example.makeupbookingapp.models.Appointment;
import com.example.makeupbookingapp.models.User;
import com.example.makeupbookingapp.services.AppointmentService;
import com.example.makeupbookingapp.services.EmailService;
import com.example.makeupbookingapp.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;
    private final UserService userService;
    private final EmailService emailService;

    public AppointmentController(AppointmentService appointmentService, UserService userService, EmailService emailService) {
        this.appointmentService = appointmentService;
        this.userService = userService;
        this.emailService = emailService;
    }

    @PostMapping("/book")
    public ResponseEntity<String> bookAppointment(@RequestBody Appointment appointment) {
        Appointment savedAppointment = appointmentService.saveAppointment(appointment);

        // Lấy thông tin người dùng để gửi email
        Optional<User> user = userService.getUserById(appointment.getUserId());
        if (user.isPresent()) {
            String bookingDetails = "Ngày: " + appointment.getDate() + "\nGiờ: " + appointment.getTime();
            emailService.sendBookingConfirmation(user.get().getEmail(), bookingDetails);
        }

        return ResponseEntity.ok("Đặt lịch thành công! Email xác nhận đã được gửi.");
    }

    @PostMapping("/payment/{appointmentId}") // 🔥 ĐÃ CHUYỂN VÀO TRONG CLASS
    public ResponseEntity<String> updatePaymentStatus(@PathVariable Long appointmentId, @RequestParam String status) {
        Optional<Appointment> appointment = appointmentService.getAppointmentById(appointmentId);

        if (appointment.isPresent()) {
            Appointment updatedAppointment = appointment.get();
            updatedAppointment.setPaymentStatus(status);
            appointmentService.saveAppointment(updatedAppointment);

            // Gửi email thông báo trạng thái thanh toán
            Optional<User> user = userService.getUserById(updatedAppointment.getUserId());
            if (user.isPresent()) {
                emailService.sendPaymentStatus(user.get().getEmail(), status);
            }

            return ResponseEntity.ok("Cập nhật trạng thái thanh toán thành công! Email đã được gửi.");
        }

        return ResponseEntity.badRequest().body("Không tìm thấy lịch hẹn!");
    }
}
