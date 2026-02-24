package com.housing.society.controller;

import com.housing.society.dto.PremiseRequestDto;
import com.housing.society.dto.PremiseResponseDto;
import com.housing.society.service.PremiseService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
public class PremiseController {

    private final PremiseService premiseService;

    public PremiseController(PremiseService premiseService) {
        this.premiseService = premiseService;
    }

    @PostMapping("/addPremise")
    @ResponseStatus(HttpStatus.CREATED)
    public PremiseResponseDto addPremise(@Valid @RequestBody PremiseRequestDto request) {
        return premiseService.addPremise(request);
    }

    @PostMapping("/premise/{id}/member/{memberId}")
    public PremiseResponseDto assignMember(@PathVariable("id") Long premiseDbId,
                                           @PathVariable("memberId") Long memberId,
                                           @RequestParam(value = "linkRenter", defaultValue = "false") boolean linkRenter) {
        return premiseService.assignMember(premiseDbId, memberId, linkRenter);
    }
}
