package org.ourhouse.backend.common.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.*;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtTokenProvider {

    private final UserDetailsService userDetailsService;
    private String secretKey = "";

    private final long tokenValidMillisecond = 1000L * 60 * 60; // 1 hour

    @PostConstruct
    public void init() {
        log.info("[init] JwtTokenProvider 내 secretKey 초기화 시작");
        secretKey = Base64.getEncoder().encodeToString(generateSecret().getBytes(StandardCharsets.UTF_8));
        log.info("[init] JwtTokenProvider 내 secretKey 초기화 완료");
    }

    // 토큰 생성
    public String createToken(String email, List<String> roles){
        log.info("[createToken] 토큰 생성");

        Claims claims = Jwts.claims().subject(email).add("roles", roles).build();

        Date now = new Date();

        String token = Jwts.builder()
                .claims(claims)
                .issuedAt(now)
                .expiration(new Date(now.getTime() + tokenValidMillisecond))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();

        log.info("[createToken] 토큰 생성 완료");

        return token;
    }

    // 토큰 인증정보 조회
    public Authentication getAuthentication(String token){
        log.info("[getAuthentication] 토큰 인증 정보 조회 시작");

        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUsername(token));

        log.info("[getAuthentication] 토큰 인증 정보 조회 완료, UserDetails email : {}", userDetails.getUsername()); // email 정보 조회

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUsername(String token){
        log.info("[getUsername] 토큰 기반 회원 구별 정보 추출");

        String info = Jwts.parser().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().getSubject();

        log.info("[getUsername] 토큰 기반 회원 정보 추출 완료, info : {} ", info);

        return info;
    }

    public String resolveToken(HttpServletRequest request){
        log.info("[resolveToken] Http Header Token 추출");

        return request.getHeader("Authorization");
    }

    public boolean validateToken(String token){
        log.info("[validateToken] 토큰 유효 체크 시작");

        try{
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).build().parseClaimsJws(token);

            return !claims.getBody().getExpiration().before(new Date());
        }catch (Exception e){
            log.info("[error] {}", e.getMessage());
            log.info("[validateToken] 토큰 유효 체크 예외 발생");
            return false;
        }

    }

    public String generateSecret(){
        SecureRandom secureRandom = new SecureRandom();

        byte[] keyBytes = new byte[32];

        secureRandom.nextBytes(keyBytes);

        return Base64.getEncoder().encodeToString(keyBytes);
    }

}
