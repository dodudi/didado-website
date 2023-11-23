package com.website.didado.domain.member.service.impl;

import com.website.didado.domain.member.domain.Member;
import com.website.didado.domain.member.dto.MemberParameter;
import com.website.didado.domain.member.dto.MemberResponse;
import com.website.didado.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    public MemberResponse signUp(MemberParameter memberParameter) {
        long count = memberRepository.countByEmail(memberParameter.fullEmail());
        if (count > 0L)
            throw new IllegalStateException("이미 존재하는 회원입니다.");

        Member save = memberRepository.save(memberParameter.toMember());
        log.debug("Signup Member={}", save);
        return new MemberResponse("회원가입에 성공했습니다.", HttpStatus.OK.value(), "");
    }

    @Override
    public MemberResponse findAll() {
        List<Member> members = memberRepository.findAll();
        log.debug("Find All Members={}", members);
        return new MemberResponse("조회에 성공했습니다.", HttpStatus.OK.value(), members);
    }


}
