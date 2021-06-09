package com.example.nonsecurityjwt.dto;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class MemberDto {

    @Id
    private String email;

    private String password;

    @Builder
    public MemberDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
