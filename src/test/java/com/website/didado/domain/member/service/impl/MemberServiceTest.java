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
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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

    @Nested
    @DisplayName("회원 삭제 테스트")
    class RemoveTest {
        @Test
        @DisplayName("성공 테스트")
        void success() throws JsonProcessingException {
            //given
            MemberParameter memberParameter = new MemberParameter("username", "firstEmail", "lastEmail", "password");
            Member member = new Member(1L, memberParameter.username(), memberParameter.fullEmail(), memberParameter.password());
            MemberResponse memberResponse = new MemberResponse("회원 탈퇴에 성공했습니다.", 200, member);

            when(memberRepository.findByUsernameAndEmail(memberParameter.username(), memberParameter.fullEmail()))
                    .thenReturn(Optional.of(member));

            //when
            MemberResponse result = memberService.removeMember(memberParameter);

            //then
            ObjectMapper mapper = new ObjectMapper();
            String data = mapper.writeValueAsString(result.data());
            Member readValue = mapper.readValue(data, Member.class);

            assertThat(result).isEqualTo(memberResponse);
            assertThat(result.data()).isEqualTo(member);
            assertThat(readValue.getId()).isEqualTo(1L);
        }

        @Test
        @DisplayName("존재하지 않는 회원 - 실패")
        void throwIllegalStateException() throws JsonProcessingException {
            //given
            MemberParameter memberParameter = new MemberParameter("username", "firstEmail", "lastEmail", "password");
            Member member = new Member(1L, memberParameter.username(), memberParameter.fullEmail(), memberParameter.password());
            MemberResponse memberResponse = new MemberResponse("회원 탈퇴에 성공했습니다.", 200, member);

            when(memberRepository.findByUsernameAndEmail(memberParameter.username(), memberParameter.fullEmail()))
                    .thenReturn(Optional.ofNullable(null));

            //then
            assertThatThrownBy(() -> memberService.removeMember(memberParameter))
                    .isInstanceOf(IllegalStateException.class);
        }
    }
}