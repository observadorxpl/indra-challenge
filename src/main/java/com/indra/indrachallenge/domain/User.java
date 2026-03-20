package com.indra.indrachallenge.domain;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private UUID id;
    private String name;
    private String email;
    private String password;
    private LocalDateTime dateCreated;
    private LocalDateTime dateModified;
    private LocalDateTime lastLogin;
    private boolean active;
    private List<Phone> phones;
}
