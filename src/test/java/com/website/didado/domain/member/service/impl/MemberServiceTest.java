package com.website.didado.domain.member.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.website.didado.domain.member.domain.Member;
import com.website.didado.domain.member.dto.MemberParameter;
import com.website.didado.domain.member.dto.MemberResponse;
import com.website.didado.domain.member.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    MemberRepository memberRepository;

    @InjectMocks
    private MemberServiceImpl memberService;

    @Test
    void signUp() {
        MemberParameter memberParameter = new MemberParameter("username", "firstEmail", "lastEmail", "password");
        MemberResponse memberResponse = new MemberResponse("회원가입에 성공했습니다.", 200, "");

        //given
        when(memberRepository.countByEmail(memberParameter.fullEmail())).thenReturn(0L);

        //when
        MemberResponse result = memberService.signUp(memberParameter);

        //then
        Assertions.assertThat(result).isEqualTo(memberResponse);
    }

    @Test
    void findAll() throws JsonProcessingException {
        MemberParameter memberParameter = new MemberParameter("username", "firstEmail", "lastEmail", "password");
        MemberResponse memberResponse = new MemberResponse("회원가입에 성공했습니다.", 200, "");

        List<Member> members = List.of(new Member("username", "firstEmail@lastEmail", "password"));
        when(memberRepository.findAll()).thenReturn(members);

        memberService.signUp(memberParameter);


        MemberResponse result = memberService.findAll();

        ObjectMapper mapper = new ObjectMapper();
        String jsonArray = mapper.writeValueAsString(result.data());
        System.out.println(result);
        List<Member> myObjectList = mapper.readValue(jsonArray, new TypeReference<>() {
        });
        Assertions.assertThat(myObjectList.size()).isEqualTo(1);
    }
}