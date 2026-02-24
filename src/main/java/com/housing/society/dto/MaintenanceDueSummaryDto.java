package com.housing.society.dto;

import java.math.BigDecimal;
import java.util.List;

public class MaintenanceDueSummaryDto {

    private Long memberId;
    private String memberName;
    private BigDecimal principal;
    private BigDecimal interest;
    private BigDecimal totalDue;
    private List<MaintenanceDueItemDto> items;

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

    public List<MaintenanceDueItemDto> getItems() {
        return items;
    }

    public void setItems(List<MaintenanceDueItemDto> items) {
        this.items = items;
    }
}
