package com.website.didado.domain.member.service.impl;

import com.website.didado.domain.member.domain.Member;
import com.website.didado.domain.member.dto.MemberParameter;
import com.website.didado.domain.member.dto.MemberResponse;
import com.website.didado.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    public MemberResponse search(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));
        log.debug("Member Search By Id -> {}", member);
        return new MemberResponse("회원 조회에 성공했습니다.", 200, member);
    }

    @Override
    @Transactional
    public MemberResponse update(Long id, MemberParameter memberParameter) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));

        member.updateMember(memberParameter);

        return new MemberResponse("회원 업데이트에 성공했습니다.", 200, member);
    }

    @Override
    public MemberResponse removeMember(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("존재하지 않는 회원입니다."));

        memberRepository.delete(member);
        return new MemberResponse("회원 탈퇴에 성공했습니다.", 200, member);
    }
}
