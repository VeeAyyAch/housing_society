package com.housing.society.serviceimpl;

import com.housing.society.dto.PremiseRequestDto;
import com.housing.society.dto.PremiseResponseDto;
import com.housing.society.entity.Member;
import com.housing.society.entity.MemberType;
import com.housing.society.entity.Premise;
import com.housing.society.repository.MemberRepository;
import com.housing.society.repository.PremiseRepository;
import com.housing.society.service.PremiseService;
import com.housing.society.utils.DtoMapper;
import com.housing.society.utils.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PremiseServiceImpl implements PremiseService {

    private final PremiseRepository premiseRepository;
    private final MemberRepository memberRepository;

    public PremiseServiceImpl(PremiseRepository premiseRepository, MemberRepository memberRepository) {
        this.premiseRepository = premiseRepository;
        this.memberRepository = memberRepository;
    }

    @Override
    @Transactional
    public PremiseResponseDto addPremise(PremiseRequestDto request) {
        premiseRepository.findByPremiseId(request.getPremiseId()).ifPresent(p -> {
            throw new IllegalArgumentException("premiseId already exists: " + request.getPremiseId());
        });

        Premise entity = DtoMapper.toEntity(request);
        Premise saved = premiseRepository.save(entity);
        return DtoMapper.toDto(saved);
    }

    @Override
    @Transactional
    public PremiseResponseDto assignMember(Long premiseDbId, Long memberId, boolean linkRenter) {
        Premise premise = premiseRepository.findById(premiseDbId)
                .orElseThrow(() -> new ResourceNotFoundException("Premise not found with id: " + premiseDbId));

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with id: " + memberId));

        if (linkRenter) {
            if (member.getType() != MemberType.RENTER) {
                throw new IllegalArgumentException("Member type must be RENTER to assign as renter");
            }

            premise.setRenter(member);
            premise.setRented(true);
        } else {
            if (member.getType() != MemberType.OWNER) {
                throw new IllegalArgumentException("Member type must be OWNER to assign as owner");
            }

            premise.setOwner(member);
        }
        Premise saved = premiseRepository.save(premise);
        return DtoMapper.toDto(saved);
    }
}
