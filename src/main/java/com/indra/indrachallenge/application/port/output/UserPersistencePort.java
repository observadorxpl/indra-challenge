package com.indra.indrachallenge.application.port.output;

import com.indra.indrachallenge.domain.User;

import java.util.Optional;

public interface UserPersistencePort {
    Optional<User> findByEmail(String email);
    User save(User user);
}
