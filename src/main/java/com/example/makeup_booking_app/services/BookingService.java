package com.example.makeup_booking_app.services;
import com.example.makeup_booking_app.dtos.BookingDTO;
import com.example.makeup_booking_app.dtos.BookingRequest;
import com.example.makeup_booking_app.models.Booking;
import com.example.makeup_booking_app.repositories.BookingRepository;
import com.example.makeup_booking_app.repositories.CustomerRepository;
import com.example.makeup_booking_app.repositories.MakeupServiceRepository;
import com.example.makeup_booking_app.repositories.ArtistRepository;
import com.example.makeup_booking_app.repositories.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private CustomerRepository userRepository;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private MakeupServiceRepository makeupServiceRepository;

    @Autowired
    private BranchRepository branchRepository;


    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Optional<Booking> getBookingById(Long id) {

        return bookingRepository.findById(id);
    }

    public Booking createBooking(Booking booking) {
        booking.setStatus("PENDING");
        return bookingRepository.save(booking);
    }

    public Booking updateBookingStatus(Long id, String status) {
        Optional<Booking> bookingOpt = bookingRepository.findById(id);
        if (bookingOpt.isPresent()) {
            Booking booking = bookingOpt.get();
            booking.setStatus(status);
            return bookingRepository.save(booking);
        }
        throw new RuntimeException("Booking not found!");
    }
    public long countBookingsForDate(LocalDate date) {
        ZoneId zone = ZoneId.systemDefault();
        Instant startOfDay = date.atStartOfDay(zone).toInstant();
        Instant endOfDay = date.plusDays(1).atStartOfDay(zone).toInstant();
        return bookingRepository.countByAppointmentTimeBetween(startOfDay, endOfDay);
    }
    public Booking createBookingFromRequest(BookingRequest request) {
        Booking booking = toEntity(request);
        return bookingRepository.save(booking);
    }

    // Chuyển BookingRequest => Booking (để lưu vào DB)
    public Booking toEntity(BookingRequest request) {
        Booking booking = new Booking();
        booking.setAppointmentTime(request.getAppointmentTime());
        booking.setStatus("PENDING");
        booking.setPaymentStatus("UNPAID");

        booking.setCustomer(userRepository.findById(request.getCustomerId()).orElseThrow());
        booking.setArtist(artistRepository.findById(request.getArtistId()).orElseThrow());
        booking.setService(makeupServiceRepository.findById(request.getServiceId()).orElseThrow());
        booking.setBranch(branchRepository.findById(request.getBranchId()).orElseThrow());

        return booking;
    }

    // Chuyển Booking => BookingDTO (trả về cho client)
    public BookingDTO toDTO(Booking booking) {
        BookingDTO dto = new BookingDTO();
        dto.setId(booking.getId());
        dto.setCustomerId(booking.getCustomer().getId());
        dto.setArtistId(booking.getArtist().getId());
        dto.setServiceId(booking.getService().getId());
        dto.setBranchId(booking.getBranch().getId());
        dto.setAppointmentTime(booking.getAppointmentTime());
        dto.setStatus(booking.getStatus());
        dto.setPaymentStatus(booking.getPaymentStatus());
        return dto;
    }


}

