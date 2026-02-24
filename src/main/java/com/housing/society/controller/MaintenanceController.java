package com.housing.society.controller;

import com.housing.society.dto.MaintenanceDueSummaryDto;
import com.housing.society.service.MaintenanceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MaintenanceController {

    private final MaintenanceService maintenanceService;

    public MaintenanceController(MaintenanceService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }

    @GetMapping("/maintenance/dues")
    public List<MaintenanceDueSummaryDto> getAllMemberDues() {
        return maintenanceService.getAllMemberDues();
    }

    @GetMapping("/maintenance/dues/{memberId}")
    public MaintenanceDueSummaryDto getMemberDues(@PathVariable("memberId") Long memberId) {
        return maintenanceService.getMemberDues(memberId);
    }
}
