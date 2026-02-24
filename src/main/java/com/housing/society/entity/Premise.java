package com.housing.society.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "premises")
public class Premise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String premiseId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PremiseType premiseType;

    @Column(nullable = false)
    private Long monthlyCost;

    @Column(nullable = false)
    private boolean isRented;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Member owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "renter_id")
    private Member renter;

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

    public Member getOwner() {
        return owner;
    }

    public void setOwner(Member owner) {
        this.owner = owner;
    }

    public Member getRenter() {
        return renter;
    }

    public void setRenter(Member renter) {
        this.renter = renter;
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
