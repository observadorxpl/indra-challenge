package com.indra.indrachallenge.infraestructure.input.rest.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record SignUpResponse(
    UUID id,
    LocalDateTime created,
    LocalDateTime modified,
    LocalDateTime lastLogin,

    String token,
    boolean isActive) {

}