package com.housing.society.serviceimpl;

import com.housing.society.dto.MemberRequestDto;
import com.housing.society.dto.MemberResponseDto;
import com.housing.society.entity.Member;
import com.housing.society.repository.MemberRepository;
import com.housing.society.service.MemberService;
import com.housing.society.utils.DtoMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    @Transactional
    public MemberResponseDto addMember(MemberRequestDto request) {
        Member entity = DtoMapper.toEntity(request);
        Member saved = memberRepository.save(entity);
        return DtoMapper.toDto(saved);
    }
}
