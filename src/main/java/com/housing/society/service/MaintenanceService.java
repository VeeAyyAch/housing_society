package com.housing.society.service;

import com.housing.society.dto.MaintenanceDueSummaryDto;

import java.util.List;

public interface MaintenanceService {

    List<MaintenanceDueSummaryDto> getAllMemberDues();

    MaintenanceDueSummaryDto getMemberDues(Long memberId);
}
