package com.example.nonsecurityjwt.service;

import com.example.nonsecurityjwt.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository MemberRepository;

    public boolean signIn(String email, String password) {
        return MemberRepository.findByEmailAndPassword(email, password).isPresent();
    }
}
