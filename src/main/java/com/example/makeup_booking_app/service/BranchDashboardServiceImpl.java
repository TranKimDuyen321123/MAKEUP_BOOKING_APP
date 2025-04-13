package com.example.makeup_booking_app.service;

import com.example.makeup_booking_app.model.Branch;
import com.example.makeup_booking_app.repository.BranchRepository;
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
    public BranchDashboardData getDashboardData(String branchId) {
        Optional<Branch> branchOptional = branchRepository.findById(branchId);
        if (branchOptional.isPresent()) {
            Branch branch = branchOptional.get();
            LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
            LocalDateTime endOfDay = LocalDate.now().atTime(LocalTime.MAX);
            long bookingsTodayLong = appointmentRepository.countByBranchIdAndAppointmentTimeBetween(branchId, startOfDay, endOfDay);
            int bookingsToday = (int) bookingsTodayLong;
            List<ServiceStat> serviceStats = new ArrayList<>();
            List<Object[]> serviceCounts = appointmentServiceRepository.countServicesByBranchId(branchId);
            for (Object[] result : serviceCounts) {
                String serviceName = (String) result[0];
                String countResultString = (String) result[1]; // Giá trị count đang là String
                try {
                    int count = Integer.parseInt(countResultString); // Chuyển String thành int
                    serviceStats.add(new ServiceStat(serviceName, count));
                } catch (NumberFormatException e) {
                    // Xử lý trường hợp chuỗi không phải là số hợp lệ (ví dụ: log lỗi)
                    System.err.println("Error parsing count: " + countResultString + " is not a valid integer.");
                    // Bạn có thể chọn bỏ qua service stat này hoặc gán một giá trị mặc định (ví dụ: 0)
                    // serviceStats.add(new ServiceStat(serviceName, 0));
                }
            }
            return new BranchDashboardData(branch.getName(), bookingsToday, serviceStats);
        } else {
            return null;
        }
    }
}
