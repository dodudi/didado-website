package com.website.didado.domain.member.application;

import com.website.didado.domain.member.dto.MemberParameter;
import com.website.didado.domain.member.dto.MemberResponse;
import com.website.didado.domain.member.service.impl.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/resister")
    public ResponseEntity<MemberResponse> resister(@RequestBody MemberParameter memberParameter) {
        return ResponseEntity.ok(memberService.signUp(memberParameter));
    }

    @GetMapping("/members")
    public ResponseEntity<MemberResponse> test2() {
        return ResponseEntity.ok(memberService.findAll());
    }
}
