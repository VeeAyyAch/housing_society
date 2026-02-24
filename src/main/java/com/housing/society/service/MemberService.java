package com.housing.society.service;

import com.housing.society.dto.MemberRequestDto;
import com.housing.society.dto.MemberResponseDto;

public interface MemberService {
    MemberResponseDto addMember(MemberRequestDto request);
}
