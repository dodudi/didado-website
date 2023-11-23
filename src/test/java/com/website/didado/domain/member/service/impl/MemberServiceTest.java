package com.website.didado.domain.member.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.website.didado.domain.member.domain.Member;
import com.website.didado.domain.member.dto.MemberParameter;
import com.website.didado.domain.member.dto.MemberResponse;
import com.website.didado.domain.member.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    MemberRepository memberRepository;

    @InjectMocks
    private MemberServiceImpl memberService;

    @Nested
    @DisplayName("회원 가입 테스트")
    class SingUpTest {
        @Test
        @DisplayName("성공 테스트")
        void signup_success() {
            MemberParameter memberParameter = new MemberParameter("username", "firstEmail", "lastEmail", "password");
            MemberResponse memberResponse = new MemberResponse("회원가입에 성공했습니다.", 200, "");

            //given
            when(memberRepository.countByEmail(memberParameter.fullEmail()))
                    .thenReturn(0L);

            //when
            MemberResponse result = memberService.signUp(memberParameter);

            //then
            assertThat(result).isEqualTo(memberResponse);
        }

        @Test
        @DisplayName("중복 회원 - 예외 발생 테스트")
        void throwDuplicateMember() {
            MemberParameter memberParameter = new MemberParameter("username", "firstEmail", "lastEmail", "password");
            MemberResponse memberResponse = new MemberResponse("회원가입에 성공했습니다.", 200, "");

            //given

            when(memberRepository.countByEmail(memberParameter.fullEmail())).thenReturn(1L);

            //when
            assertThatThrownBy(() -> memberService.signUp(memberParameter))
                    .isInstanceOf(IllegalStateException.class);
        }
    }

}