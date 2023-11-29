package com.website.didado.domain.member.application.impl;

import com.website.didado.domain.member.dto.MemberParameter;
import com.website.didado.domain.member.dto.MemberResponse;

public interface MemberService {

    MemberResponse removeMember(Long id);

    MemberResponse signUp(MemberParameter memberParameter);

    MemberResponse findAll();

    MemberResponse search(Long id);

    MemberResponse update(Long id, MemberParameter memberParameter);
}
