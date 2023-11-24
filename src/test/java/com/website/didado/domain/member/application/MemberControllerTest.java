package com.website.didado.domain.member.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.website.didado.domain.member.dto.MemberParameter;
import com.website.didado.domain.member.dto.MemberResponse;
import com.website.didado.domain.member.service.impl.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@WebMvcTest(controllers = MemberController.class)
public class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService memberService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void test() throws Exception {
        MemberResponse response = new MemberResponse("회원 가입에 성공했습니다.", 200, "");
        MemberParameter parameter = new MemberParameter("username", "firstEmail", "lastEmail.com", "password");

        Mockito.when(memberService.signUp(parameter)).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.post("/resister")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(parameter))
                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andExpect(status().isOk())
                .andDo(print());
        log.info("컨트롤러 테스트");
    }
}
