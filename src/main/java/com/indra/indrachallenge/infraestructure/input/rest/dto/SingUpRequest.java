package com.indra.indrachallenge.infraestructure.input.rest.dto;

import com.indra.indrachallenge.domain.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.util.List;

@Builder
public record SingUpRequest (
    @NotBlank(message = "El nombre es obligatorio")
    String name,
    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "Formato de email inválido")
    String email,
    @NotBlank(message = "La contraseña es obligatoria")
    String password,
    List<Phone> phones){

    public User toDomain(){
        return User.builder()
            .name(this.name)
            .email(this.email)
            .password(this.password)
            .phones(toPhones())
            .build();
    }

    List<com.indra.indrachallenge.domain.Phone> toPhones(){
        return this.phones.stream().map(Phone::toPhone).toList();
    }
}

