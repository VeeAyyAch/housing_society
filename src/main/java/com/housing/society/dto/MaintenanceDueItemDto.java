package com.housing.society.dto;

import java.math.BigDecimal;

public class MaintenanceDueItemDto {

    private Long memberId;
    private String memberName;
    private Long premiseDbId;
    private String premiseId;
    private String chargedTo;
    private int pendingMonths;
    private BigDecimal monthlyFee;
    private BigDecimal principal;
    private BigDecimal interest;
    private BigDecimal totalDue;

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Long getPremiseDbId() {
        return premiseDbId;
    }

    public void setPremiseDbId(Long premiseDbId) {
        this.premiseDbId = premiseDbId;
    }

    public String getPremiseId() {
        return premiseId;
    }

    public void setPremiseId(String premiseId) {
        this.premiseId = premiseId;
    }

    public String getChargedTo() {
        return chargedTo;
    }

    public void setChargedTo(String chargedTo) {
        this.chargedTo = chargedTo;
    }

    public int getPendingMonths() {
        return pendingMonths;
    }

    public void setPendingMonths(int pendingMonths) {
        this.pendingMonths = pendingMonths;
    }

    public BigDecimal getMonthlyFee() {
        return monthlyFee;
    }

    public void setMonthlyFee(BigDecimal monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    public BigDecimal getPrincipal() {
        return principal;
    }

    public void setPrincipal(BigDecimal principal) {
        this.principal = principal;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    public BigDecimal getTotalDue() {
        return totalDue;
    }

    public void setTotalDue(BigDecimal totalDue) {
        this.totalDue = totalDue;
    }
}
