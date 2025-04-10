package com.example.makeup_booking_app;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appointments")
@CrossOrigin(origins = "*")
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
        Optional<User> user = userService.getUserById(appointment.getUserId().longValue());

        user.ifPresent(value -> {
            String bookingDetails = "Thời gian: " + appointment.getAppointmentTime();
            emailService.sendBookingConfirmation(value.getEmail(), bookingDetails);
        });

        return ResponseEntity.ok("Đặt lịch thành công! Email xác nhận đã được gửi.");
    }

    @PostMapping("/payment/{appointmentId}")
    public ResponseEntity<String> updatePaymentStatus(@PathVariable Long appointmentId, @RequestParam String status) {
        Optional<Appointment> appointment = appointmentService.getAppointmentById(appointmentId);

        if (appointment.isPresent()) {
            Appointment updatedAppointment = appointment.get();
            updatedAppointment.setStatus(status);
            appointmentService.saveAppointment(updatedAppointment);

            Optional<User> user = userService.getUserById(updatedAppointment.getUserId().longValue());
            user.ifPresent(value -> emailService.sendPaymentStatus(value.getEmail(), status));

            return ResponseEntity.ok("Cập nhật trạng thái thanh toán thành công!");
        }

        return ResponseEntity.badRequest().body("Không tìm thấy lịch hẹn!");
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(appointmentService.getAppointmentsByUserId(userId));
    }

    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }
}
