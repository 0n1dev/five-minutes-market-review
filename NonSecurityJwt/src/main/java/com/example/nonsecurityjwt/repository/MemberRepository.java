package com.example.nonsecurityjwt.repository;

import com.example.nonsecurityjwt.dto.MemberDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberDto, String> {

    Optional<MemberDto> findByEmailAndPassword(String email, String password);
}
