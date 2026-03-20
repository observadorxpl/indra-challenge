package com.indra.indrachallenge.infraestructure.input.rest;

import com.indra.indrachallenge.application.port.input.UserServicePort;
import com.indra.indrachallenge.infraestructure.input.rest.dto.SignUpResponse;
import com.indra.indrachallenge.infraestructure.input.rest.dto.SingUpRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users")
@AllArgsConstructor
public class UserRest {
    private final UserServicePort userServicePort;

    @PostMapping("/sign-up")
    public ResponseEntity<SignUpResponse> singUp(@Valid @RequestBody SingUpRequest singUpRequest) {
        SignUpResponse signUpResponse = userServicePort.singUp(singUpRequest.toDomain());
        return ResponseEntity.ok(signUpResponse);
    }
}
