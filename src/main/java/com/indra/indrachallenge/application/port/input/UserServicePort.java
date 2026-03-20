package com.indra.indrachallenge.application.port.input;

import com.indra.indrachallenge.domain.User;
import com.indra.indrachallenge.infraestructure.input.rest.dto.SignUpResponse;

public interface UserServicePort {
     SignUpResponse singUp(User user);
}
