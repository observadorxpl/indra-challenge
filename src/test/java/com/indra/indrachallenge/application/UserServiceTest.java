package com.indra.indrachallenge.application;

import com.indra.indrachallenge.application.port.output.UserPersistencePort;
import com.indra.indrachallenge.application.service.UserService;
import com.indra.indrachallenge.domain.Phone;
import com.indra.indrachallenge.domain.User;
import com.indra.indrachallenge.infraestructure.output.config.security.TokenGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserPersistencePort userPersistencePort;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private TokenGenerator tokenGenerator;
    @Mock
    private PasswordEncoder passwordEncoder;
    @InjectMocks
    private UserService userService;

    @Test
    void shouldReturnASuccessSignUpResponse() {
        User user = User.builder()
            .name("Jose")
            .email("jcayoacu2@gmail.com")
            .password("12345")
                .phones(List.of(Phone.builder()
                        .number("978720381")
                        .cityCode("LIMA")
                        .countryCode("PE")
                    .build()))
            .build();
        Authentication auth = new UsernamePasswordAuthenticationToken("testuser", "password", Collections.emptyList());
        Jwt jwt = Jwt.withTokenValue("eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJzZWxmIiwic3ViIjoiamNheW9hY3UzQGdtYWlsLmNvbSIsImV4cCI6MTc3NDAxNjY3NywiaWF0IjoxNzc0MDE2NjE3LCJwZXJtaXNzaW9ucyI6IkFMTCIsInNjb3BlIjoiVVNFUixGQUNUT1JfUEFTU1dPUkQifQ.Fl_MQUaPjXXJmmOZ1O4_W2P7HF5QtlWuiPSGBBmetO7gl4-xT4d1pAdSqYtjJSaCfAnC7vLjW1m18w4NoLmJ0ImfMq_3_QyGTcL8FFEhz0XH5dcnfyJ6lOXvnGpaL5ncZlpIIYmwyxA3sQ9EDBv2rgMvtH3MfSXz5JI0BHTINgfn5RZjGhiSC754B1k_Raw9ux1nHma5eNlM5M5AKMkMWOi77yrf9g-Pq-yzM6UnGWGoNyytOKScim2UsVTVNpR_sB4cgdOzX04R2gsuD-aNwxr3RIEybaP01-ndZEPC2Umz3QurINJk9AoHAwPlg271xslkUvsHp7oX19Is66oXyQ").build();
        when(userPersistencePort.findByEmail(any())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(any())).thenReturn("$2a$10$Q80p4Jjlrl6rFM5MYsKdkeW4lXLFiFT0Mau8QvKGvLDLIWzfuIEJS");
        when(userPersistencePort.save(any())).thenReturn(user);
        when(authenticationManager.authenticate(any())).thenReturn(auth);
        when(tokenGenerator.generateToken(any())).thenReturn(jwt);
        userService.singUp(user);
    }
}
