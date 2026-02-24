package com.housing.society.serviceimpl;

import com.housing.society.dto.MaintenanceDueItemDto;
import com.housing.society.dto.MaintenanceDueSummaryDto;
import com.housing.society.entity.Member;
import com.housing.society.entity.Premise;
import com.housing.society.entity.PremiseType;
import com.housing.society.repository.MemberRepository;
import com.housing.society.repository.PremiseRepository;
import com.housing.society.service.MaintenanceService;
import com.housing.society.utils.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {

    private static final BigDecimal INTEREST_RATE = new BigDecimal("0.015");
    private static final BigDecimal RENTED_PREMISE_ADDON = new BigDecimal("500");

    private final PremiseRepository premiseRepository;
    private final MemberRepository memberRepository;

    public MaintenanceServiceImpl(PremiseRepository premiseRepository, MemberRepository memberRepository) {
        this.premiseRepository = premiseRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    public List<MaintenanceDueSummaryDto> getAllMemberDues() {
        LocalDate today = LocalDate.now();
        List<Premise> premises = premiseRepository.findAll();

        Map<Long, MaintenanceDueSummaryDto> summaries = new LinkedHashMap<>();

        for (Premise premise : premises) {
            Member responsible = getResponsibleMember(premise);
            if (responsible == null) {
                continue;
            }

            MaintenanceDueItemDto item = calculatePremiseDue(premise, responsible, today);
            if (item.getPendingMonths() <= 0) {
                continue;
            }

            MaintenanceDueSummaryDto summary = summaries.get(responsible.getId());
            if (summary == null) {
                summary = new MaintenanceDueSummaryDto();
                summary.setMemberId(responsible.getId());
                summary.setMemberName(responsible.getName());
                summary.setPrincipal(BigDecimal.ZERO);
                summary.setInterest(BigDecimal.ZERO);
                summary.setTotalDue(BigDecimal.ZERO);
                summary.setItems(new ArrayList<>());
                summaries.put(responsible.getId(), summary);
            }

            summary.getItems().add(item);
            summary.setPrincipal(summary.getPrincipal().add(item.getPrincipal()));
            summary.setInterest(summary.getInterest().add(item.getInterest()));
            summary.setTotalDue(summary.getTotalDue().add(item.getTotalDue()));
        }

        List<MaintenanceDueSummaryDto> result = new ArrayList<>(summaries.values());
        result.sort(Comparator.comparing(MaintenanceDueSummaryDto::getMemberId));
        return result;
    }

    @Override
    public MaintenanceDueSummaryDto getMemberDues(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with id: " + memberId));

        LocalDate today = LocalDate.now();
        List<Premise> premises = premiseRepository.findAll();

        MaintenanceDueSummaryDto summary = new MaintenanceDueSummaryDto();
        summary.setMemberId(member.getId());
        summary.setMemberName(member.getName());
        summary.setPrincipal(BigDecimal.ZERO);
        summary.setInterest(BigDecimal.ZERO);
        summary.setTotalDue(BigDecimal.ZERO);
        summary.setItems(new ArrayList<>());

        for (Premise premise : premises) {
            Member responsible = getResponsibleMember(premise);
            if (responsible == null || !responsible.getId().equals(memberId)) {
                continue;
            }

            MaintenanceDueItemDto item = calculatePremiseDue(premise, responsible, today);
            if (item.getPendingMonths() <= 0) {
                continue;
            }

            summary.getItems().add(item);
            summary.setPrincipal(summary.getPrincipal().add(item.getPrincipal()));
            summary.setInterest(summary.getInterest().add(item.getInterest()));
            summary.setTotalDue(summary.getTotalDue().add(item.getTotalDue()));
        }

        return summary;
    }

    private Member getResponsibleMember(Premise premise) {
        if (premise == null) {
            return null;
        }

        if (premise.isRented() && premise.getRenter() != null) {
            return premise.getRenter();
        }

        return premise.getOwner();
    }

    private MaintenanceDueItemDto calculatePremiseDue(Premise premise, Member responsible, LocalDate today) {
        BigDecimal monthlyFee = getMonthlyMaintenanceFee(premise);

        LocalDate paidThrough = calculatePaidThroughDateWithWaiver(premise);
        int pendingMonths = calculatePendingMonths(paidThrough, today);

        BigDecimal principal = monthlyFee.multiply(BigDecimal.valueOf(pendingMonths));

        BigDecimal interest = BigDecimal.ZERO;
        if (pendingMonths > 0 && today.getDayOfMonth() > 10) {
            interest = principal.multiply(INTEREST_RATE);
        }

        principal = principal.setScale(2, RoundingMode.HALF_UP);
        interest = interest.setScale(2, RoundingMode.HALF_UP);

        MaintenanceDueItemDto item = new MaintenanceDueItemDto();
        item.setMemberId(responsible.getId());
        item.setMemberName(responsible.getName());
        item.setPremiseDbId(premise.getId());
        item.setPremiseId(premise.getPremiseId());
        item.setChargedTo(premise.isRented() && premise.getRenter() != null ? "RENTER" : "OWNER");
        item.setPendingMonths(pendingMonths);
        item.setMonthlyFee(monthlyFee.setScale(2, RoundingMode.HALF_UP));
        item.setPrincipal(principal);
        item.setInterest(interest);
        item.setTotalDue(principal.add(interest).setScale(2, RoundingMode.HALF_UP));
        return item;
    }

    private BigDecimal getMonthlyMaintenanceFee(Premise premise) {
        PremiseType type = premise.getPremiseType();

        BigDecimal base;
        if (type == PremiseType.BHK_1) {
            base = new BigDecimal("500");
        } else if (type == PremiseType.BHK_2) {
            base = new BigDecimal("700");
        } else if (type == PremiseType.BHK_3) {
            base = new BigDecimal("1000");
        } else if (type == PremiseType.VILLA) {
            base = new BigDecimal("2000");
        } else if (type == PremiseType.SHOP) {
            base = new BigDecimal("400");
        } else {
            base = BigDecimal.ZERO;
        }

        if (premise.isRented()) {
            base = base.add(RENTED_PREMISE_ADDON);
        }

        return base;
    }

    private LocalDate calculatePaidThroughDateWithWaiver(Premise premise) {
        LocalDate lastPaidOn = premise.getLastPaidOn();
        LocalDate advancePaidTill = premise.getAdvancePaidTill();

        LocalDate paidThrough = advancePaidTill != null ? advancePaidTill : lastPaidOn;

        if (lastPaidOn != null && advancePaidTill != null) {
            YearMonth last = YearMonth.from(lastPaidOn);
            YearMonth advance = YearMonth.from(advancePaidTill);
            int monthsBetween = monthsBetweenInclusive(last, advance);

            if (monthsBetween >= 11) {
                paidThrough = advancePaidTill.plusMonths(1);
            }
        }

        return paidThrough;
    }

    private int calculatePendingMonths(LocalDate paidThrough, LocalDate today) {
        YearMonth currentMonth = YearMonth.from(today);

        if (paidThrough == null) {
            return 1;
        }

        YearMonth paidMonth = YearMonth.from(paidThrough);
        YearMonth nextDueMonth = paidMonth.plusMonths(1);

        if (!nextDueMonth.isBefore(currentMonth) && !nextDueMonth.equals(currentMonth)) {
            return 0;
        }

        if (nextDueMonth.isAfter(currentMonth)) {
            return 0;
        }

        return monthsBetweenInclusive(nextDueMonth, currentMonth);
    }

    private int monthsBetweenInclusive(YearMonth start, YearMonth end) {
        int startIndex = start.getYear() * 12 + start.getMonthValue();
        int endIndex = end.getYear() * 12 + end.getMonthValue();
        int diff = endIndex - startIndex;
        return diff >= 0 ? diff + 1 : 0;
    }
}
