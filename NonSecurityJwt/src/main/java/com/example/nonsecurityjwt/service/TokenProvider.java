package com.example.nonsecurityjwt.service;

import com.example.nonsecurityjwt.dto.MemberDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Calendar;
import java.util.Date;

@Service
public class TokenProvider {

    private SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // 토큰 암호화 키
    private int tokenExpirationMs = 1800000; // 토큰 만료시간

    public String createToken(MemberDto member) {

        // setExpiration 매개변수가 Date로 되어있어 LocalDateTime를 사용하지 못함
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MILLISECOND, tokenExpirationMs);
        Date expiryDate =  calendar.getTime();

        return Jwts.builder()
                .claim("member", member)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
        Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
        return true;
    }
}
