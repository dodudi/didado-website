package com.website.didado.domain.member.service.impl;

import com.website.didado.domain.member.dto.MemberParameter;
import com.website.didado.domain.member.dto.MemberResponse;

public interface MemberService {
    MemberResponse signUp(MemberParameter memberParameter);
    MemberResponse findAll();
}
