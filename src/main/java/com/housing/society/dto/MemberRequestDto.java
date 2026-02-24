package com.housing.society.dto;

import com.housing.society.entity.MemberType;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MemberRequestDto {

    @NotBlank
    private String name;

    @NotBlank
    private String phone;

    @NotNull
    @Schema(allowableValues = {"RENTER", "OWNER"})
    private MemberType type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public MemberType getType() {
        return type;
    }

    public void setType(MemberType type) {
        this.type = type;
    }
}
