package com.example.makeup_booking_app.service;

import com.example.makeup_booking_app.dto.SystemStatsResponse;
import com.example.makeup_booking_app.repository.*;
import com.example.makeup_booking_app.service.DashboardStatsService;
import org.springframework.stereotype.Service;

@Service
public class DashboardStatsServiceImpl implements DashboardStatsService {

    private final BranchRepository branchRepository;
    private final MakeupServiceRepository makeupServiceRepository;
    private final MakeupArtistRepository makeupArtistRepository;
    private final AppointmentRepository appointmentRepository;

    public DashboardStatsServiceImpl(
            BranchRepository branchRepository,
            MakeupServiceRepository makeupServiceRepository,
            MakeupArtistRepository makeupArtistRepository,
            AppointmentRepository appointmentRepository) {
        this.branchRepository = branchRepository;
        this.makeupServiceRepository = makeupServiceRepository;
        this.makeupArtistRepository = makeupArtistRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public SystemStatsResponse getSystemStats() {
        return new SystemStatsResponse(
                branchRepository.count(),
                makeupServiceRepository.count(),
                makeupArtistRepository.count(),
                appointmentRepository.count()
        );
    }
}
