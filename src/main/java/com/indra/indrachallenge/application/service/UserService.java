package com.indra.indrachallenge.application.service;

import com.indra.indrachallenge.application.port.input.UserServicePort;
import com.indra.indrachallenge.application.port.output.UserPersistencePort;
import com.indra.indrachallenge.domain.User;
import com.indra.indrachallenge.infraestructure.output.config.security.CustomAuthenticationException;
import com.indra.indrachallenge.infraestructure.output.config.security.TokenGenerator;
import com.indra.indrachallenge.infraestructure.input.rest.dto.SignUpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class UserService implements UserServicePort {
    private final UserPersistencePort userPersistencePort;
    private final AuthenticationManager authenticationManager;
    private final TokenGenerator tokenGenerator;
    private final PasswordEncoder passwordEncoder;
    @Override
    public SignUpResponse singUp(User user) {
        userPersistencePort.findByEmail(user.getEmail()).ifPresent(userEntity -> {
            throw new CustomAuthenticationException("El correo ya está registrado");
        });
        user.setActive(true);
        String password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        user.setDateCreated(LocalDateTime.now());
        user.setLastLogin(LocalDateTime.now());
        User user1 =  userPersistencePort.save(user);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), password));
        Jwt jwt = tokenGenerator.generateToken(authentication);
        String token = jwt.getTokenValue();
        return SignUpResponse.builder()
            .id(user1.getId())
            .created(user1.getDateCreated())
            .modified(user1.getDateModified())
            .lastLogin(user.getLastLogin())
            .token(token)
            .isActive(user1.isActive())
            .build();
    }
}
