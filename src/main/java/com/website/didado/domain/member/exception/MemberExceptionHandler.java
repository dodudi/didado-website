package com.website.didado.domain.member.exception;

import com.website.didado.domain.member.api.MemberController;
import com.website.didado.domain.member.dto.MemberResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice(basePackageClasses = MemberController.class)
public class MemberExceptionHandler {
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
