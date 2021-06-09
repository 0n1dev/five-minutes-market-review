package com.example.nonsecurityjwt.controller;

import com.example.nonsecurityjwt.dto.MemberDto;
import com.example.nonsecurityjwt.dto.ResponseDto;
import com.example.nonsecurityjwt.service.MemberService;
import com.example.nonsecurityjwt.service.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final TokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseDto signIn(@RequestBody MemberDto member) {
        boolean isLogin = memberService.signIn(member.getEmail(), member.getPassword());
        
        if (isLogin) {
            String token = tokenProvider.createToken(member);

            return ResponseDto.builder()
                    .statusCode(HttpStatus.OK)
                    .message(token)
                    .build();
        }

        return ResponseDto.builder()
                .statusCode(HttpStatus.NOT_FOUND)
                .message("로그인 실패")
                .build();
    }
}
