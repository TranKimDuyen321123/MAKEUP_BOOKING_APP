package com.example.makeup_booking_app.service;

import com.example.makeup_booking_app.Branch;
import com.example.makeup_booking_app.BranchRepository;
import com.example.makeup_booking_app.repository.AppointmentRepository;
import com.example.makeup_booking_app.repository.AppointmentServiceRepository;
import com.example.makeup_booking_app.repository.MakeupServiceRepository;
import com.example.makeup_booking_app.dto.BranchDashboardData;
import com.example.makeup_booking_app.dto.ServiceStat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        if (branchOptional.isPresent()) {
            Branch branch = branchOptional.get();
            LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
            LocalDateTime endOfDay = LocalDate.now().atTime(LocalTime.MAX);

            long bookingsToday = appointmentRepository.countByBranchIdAndAppointmentTimeBetween(
                    branchId, startOfDay, endOfDay
            );
            String bookingsTodayStr = String.valueOf(bookingsToday); // ✅ Đã fix

            List<ServiceStat> serviceStats = new ArrayList<>();
            List<Object[]> serviceCounts = appointmentServiceRepository.countServicesByBranchId(branchId);
            for (Object[] result : serviceCounts) {
                String serviceName = (String) result[0];
                int count = ((Number) result[1]).intValue(); // ✅ Đã fix
                serviceStats.add(new ServiceStat(serviceName, count));
            }

            return new BranchDashboardData(branch.getName(), bookingsTodayStr, serviceStats);
        } else {
            return null;
        }
    }
}
