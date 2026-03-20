package com.indra.indrachallenge.infraestructure.output.config.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

import static java.time.Instant.now;

@Component
@AllArgsConstructor
public class TokenGenerator {
    private final JwtEncoder encoder;

    public Jwt generateToken(Authentication authentication) {
        Instant now = now();

        var authorities = authentication.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(","));

        JwtClaimsSet claims = JwtClaimsSet.builder()
            .issuer("self")
            .issuedAt(now)
            .expiresAt(now.plus(1, ChronoUnit.MINUTES))
            .subject(authentication.getName())
            .claim("permissions", "ALL")
            .claim("scope", authorities)
            .build();

        return  encoder.encode(JwtEncoderParameters.from(claims));
    }
}
