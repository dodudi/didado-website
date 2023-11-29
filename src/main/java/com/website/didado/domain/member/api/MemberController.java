package com.website.didado.domain.member.api;

import com.website.didado.domain.member.dto.MemberParameter;
import com.website.didado.domain.member.dto.MemberResponse;
import com.website.didado.domain.member.application.impl.MemberService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/members")
    public ResponseEntity<MemberResponse> resister(@Valid @RequestBody MemberParameter memberParameter) {
        return ResponseEntity.ok(memberService.signUp(memberParameter));
    }

    @GetMapping("/members")
    public ResponseEntity<MemberResponse> searches() {
        return ResponseEntity.ok(memberService.findAll());
    }

    @GetMapping("/members/{id}")
    public ResponseEntity<MemberResponse> search(@PathVariable Long id) {
        return ResponseEntity.ok(memberService.search(id));
    }

    @PostMapping("/members/{id}/delete")
    public ResponseEntity<MemberResponse> delete(@PathVariable Long id) {
        return ResponseEntity.ok(memberService.removeMember(id));
    }

    @PostMapping("/members/{id}/update")
    public ResponseEntity<MemberResponse> update(@PathVariable Long id, @RequestBody MemberParameter memberParameter) {
        return ResponseEntity.ok(memberService.update(id, memberParameter));
    }
}