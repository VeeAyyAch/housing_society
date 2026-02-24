package com.housing.society.dto;

import com.housing.society.entity.PremiseType;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;

public class PremiseRequestDto {

    @NotBlank
    private String premiseId;

    @NotNull
    @Schema(allowableValues = {"1bhk", "2bhk", "3bhk", "villa", "shop"})
    private PremiseType premiseType;

    @NotNull
    @Positive
    private Long monthlyCost;

    private boolean isRented;

    private LocalDate lastPaidOn;

    private LocalDate advancePaidTill;

    public String getPremiseId() {
        return premiseId;
    }

    public void setPremiseId(String premiseId) {
        this.premiseId = premiseId;
    }

    public PremiseType getPremiseType() {
        return premiseType;
    }

    public void setPremiseType(PremiseType premiseType) {
        this.premiseType = premiseType;
    }

    public Long getMonthlyCost() {
        return monthlyCost;
    }

    public void setMonthlyCost(Long monthlyCost) {
        this.monthlyCost = monthlyCost;
    }

    public boolean isRented() {
        return isRented;
    }

    public void setRented(boolean rented) {
        isRented = rented;
    }

    public LocalDate getLastPaidOn() {
        return lastPaidOn;
    }

    public void setLastPaidOn(LocalDate lastPaidOn) {
        this.lastPaidOn = lastPaidOn;
    }

    public LocalDate getAdvancePaidTill() {
        return advancePaidTill;
    }

    public void setAdvancePaidTill(LocalDate advancePaidTill) {
        this.advancePaidTill = advancePaidTill;
    }
}
