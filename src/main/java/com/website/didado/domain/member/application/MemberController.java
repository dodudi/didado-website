package com.website.didado.domain.member.application;

import com.website.didado.domain.member.dto.MemberParameter;
import com.website.didado.domain.member.dto.MemberResponse;
import com.website.didado.domain.member.exception.DuplicateMemberException;
import com.website.didado.domain.member.exception.NotFoundMemberException;
import com.website.didado.domain.member.service.impl.MemberService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Object> throwMethodArgumentNotValidHandler(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();

        Map<String, String> validResult = new HashMap<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            validResult.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        MemberResponse response = new MemberResponse("유효성 검사에 실패했습니다.", e.getStatusCode().value(), validResult);
        return ResponseEntity
                .status(e.getStatusCode().value())
                .body(response);
    }

    @ExceptionHandler({DuplicateMemberException.class})
    public ResponseEntity<Object> throwDuplicateMemberHandler(DuplicateMemberException e) {
        MemberResponse response = new MemberResponse(e.getMessage(), e.getStatus(), e.getData());
        return ResponseEntity
                .status(e.getStatus())
                .body(response);
    }

    @ExceptionHandler({NotFoundMemberException.class})
    public ResponseEntity<Object> throwNotFoundMemberHandler(NotFoundMemberException e) {
        MemberResponse response = new MemberResponse(e.getMessage(), e.getStatus(), e.getData());
        return ResponseEntity
                .status(e.getStatus())
                .body(response);
    }
}
