package com.website.didado.domain.member.application;

import com.website.didado.domain.member.dto.MemberParameter;
import com.website.didado.domain.member.dto.MemberResponse;
import com.website.didado.domain.member.service.impl.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/members")
    public ResponseEntity<MemberResponse> resister(@RequestBody MemberParameter memberParameter) {
        return ResponseEntity.ok(memberService.signUp(memberParameter));
    }

    @GetMapping("/members")
    public ResponseEntity<MemberResponse> test2() {
        return ResponseEntity.ok(memberService.findAll());
    }

    @GetMapping("/members/{id}")
    public ResponseEntity<MemberResponse> search(@PathVariable Long id) {
        return ResponseEntity.ok(memberService.search(id));
    }

    @PostMapping("/members/{id}/delete")
    public ResponseEntity<MemberResponse> delete(@PathVariable Long id){
        return ResponseEntity.ok(memberService.removeMember(id));
    }
}