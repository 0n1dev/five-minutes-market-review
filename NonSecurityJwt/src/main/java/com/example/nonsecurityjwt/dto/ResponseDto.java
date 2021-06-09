package com.example.nonsecurityjwt.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@ToString
public class ResponseDto {

    private HttpStatus statusCode;
    private String message;

    @Builder
    public ResponseDto(HttpStatus statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
