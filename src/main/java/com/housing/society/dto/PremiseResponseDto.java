package com.housing.society.dto;

import com.housing.society.entity.PremiseType;

import java.time.LocalDate;

public class PremiseResponseDto {

    private Long id;
    private String premiseId;
    private PremiseType premiseType;
    private Long monthlyCost;
    private boolean isRented;
    private Long ownerId;
    private Long renterId;
    private LocalDate lastPaidOn;
    private LocalDate advancePaidTill;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Long getRenterId() {
        return renterId;
    }

    public void setRenterId(Long renterId) {
        this.renterId = renterId;
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
