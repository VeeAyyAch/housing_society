package com.housing.society.utils;

import com.housing.society.dto.MemberRequestDto;
import com.housing.society.dto.MemberResponseDto;
import com.housing.society.dto.PremiseRequestDto;
import com.housing.society.dto.PremiseResponseDto;
import com.housing.society.entity.Member;
import com.housing.society.entity.Premise;

public final class DtoMapper {

    private DtoMapper() {
    }

    public static Member toEntity(MemberRequestDto dto) {
        Member m = new Member();
        m.setName(dto.getName());
        m.setPhone(dto.getPhone());
        m.setType(dto.getType());
        return m;
    }

    public static MemberResponseDto toDto(Member entity) {
        MemberResponseDto dto = new MemberResponseDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPhone(entity.getPhone());
        dto.setType(entity.getType());
        return dto;
    }

    public static Premise toEntity(PremiseRequestDto dto) {
        Premise p = new Premise();
        p.setPremiseId(dto.getPremiseId());
        p.setPremiseType(dto.getPremiseType());
        p.setMonthlyCost(dto.getMonthlyCost());
        p.setRented(dto.isRented());
        p.setLastPaidOn(dto.getLastPaidOn());
        p.setAdvancePaidTill(dto.getAdvancePaidTill());
        return p;
    }

    public static PremiseResponseDto toDto(Premise entity) {
        PremiseResponseDto dto = new PremiseResponseDto();
        dto.setId(entity.getId());
        dto.setPremiseId(entity.getPremiseId());
        dto.setPremiseType(entity.getPremiseType());
        dto.setMonthlyCost(entity.getMonthlyCost());
        dto.setRented(entity.isRented());
        dto.setLastPaidOn(entity.getLastPaidOn());
        dto.setAdvancePaidTill(entity.getAdvancePaidTill());
        dto.setOwnerId(entity.getOwner() != null ? entity.getOwner().getId() : null);
        dto.setRenterId(entity.getRenter() != null ? entity.getRenter().getId() : null);
        return dto;
    }
}
