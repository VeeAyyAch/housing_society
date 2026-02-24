package com.housing.society.service;

import com.housing.society.dto.PremiseRequestDto;
import com.housing.society.dto.PremiseResponseDto;

public interface PremiseService {
    PremiseResponseDto addPremise(PremiseRequestDto request);

    PremiseResponseDto assignMember(Long premiseDbId, Long memberId, boolean linkRenter);
}
