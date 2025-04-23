package com.example.makeup_booking_app.service;

import com.example.makeup_booking_app.dto.BranchDashboardData;
import com.example.makeup_booking_app.dto.ServiceStat;
import com.example.makeup_booking_app.model.Appointment;
import com.example.makeup_booking_app.model.AppointmentService;
import com.example.makeup_booking_app.model.Branch;
import com.example.makeup_booking_app.repository.AppointmentRepository;
import com.example.makeup_booking_app.repository.AppointmentServiceRepository;
import com.example.makeup_booking_app.repository.BranchRepository;
import com.example.makeup_booking_app.repository.MakeupServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Service
public class BranchDashboardServiceImpl implements BranchDashboardService {

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AppointmentServiceRepository appointmentServiceRepository;

    @Autowired
    private MakeupServiceRepository makeupServiceRepository;

    @Override
    public BranchDashboardData getDashboardData(Long branchId) {
        Optional<Branch> branchOptional = branchRepository.findById(branchId);
        if (branchOptional.isEmpty()) {
            return null;
        }

        Branch branch = branchOptional.get();
        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        LocalDateTime endOfDay = LocalDate.now().atTime(LocalTime.MAX);

        long bookingsToday = appointmentRepository.findAll().stream()
                .filter(a -> a.getMakeupArtist().getBranch().getId().equals(branchId))
                .filter(a -> {
                    LocalDateTime time = a.getAppointmentTime().toLocalDateTime();
                    return !time.isBefore(startOfDay) && !time.isAfter(endOfDay);
                })
                .count();

        List<AppointmentService> all = appointmentServiceRepository.findAll();
        Map<String, Integer> serviceCountMap = new HashMap<>();

        for (AppointmentService aps : all) {
            if (aps.getAppointment().getMakeupArtist().getBranch().getId().equals(branchId)) {
                String name = aps.getMakeupService().getName();
                serviceCountMap.put(name, serviceCountMap.getOrDefault(name, 0) + 1);
            }
        }

        List<ServiceStat> serviceStats = new ArrayList<>();
        serviceCountMap.forEach((name, count) -> serviceStats.add(new ServiceStat(name, count)));

        return new BranchDashboardData(branch.getName(), (int) bookingsToday, serviceStats);
    }
}
