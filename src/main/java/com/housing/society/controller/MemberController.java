package com.housing.society.controller;

import com.housing.society.dto.MemberRequestDto;
import com.housing.society.dto.MemberResponseDto;
import com.housing.society.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/addMember")
    @ResponseStatus(HttpStatus.CREATED)
    public MemberResponseDto addMember(@Valid @RequestBody MemberRequestDto request) {
        return memberService.addMember(request);
    }
}
