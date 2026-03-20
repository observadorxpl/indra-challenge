package com.indra.indrachallenge.infraestructure.output.persistence;

import com.indra.indrachallenge.application.port.output.UserPersistencePort;
import com.indra.indrachallenge.domain.Phone;
import com.indra.indrachallenge.domain.User;
import com.indra.indrachallenge.infraestructure.output.persistence.entity.PhoneEntity;
import com.indra.indrachallenge.infraestructure.output.persistence.entity.UserEntity;
import com.indra.indrachallenge.infraestructure.output.persistence.mapper.PhonePersistenceMapper;
import com.indra.indrachallenge.infraestructure.output.persistence.mapper.UserPersistenceMapper;
import com.indra.indrachallenge.infraestructure.output.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserPersistenceAdapter implements UserPersistencePort {
    private final UserRepository userRepository;
    private final UserPersistenceMapper mapper;
    private final PhonePersistenceMapper phoneMapper;

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email).map(mapper::toUser);
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = mapper.toUserEntity(user);
        List<Phone> phones = user.getPhones();
        userEntity.setPhones(phones.stream()
            .map(phone -> {
                PhoneEntity phoneEntity =  phoneMapper.toPhoneEntity(phone);
                phoneEntity.setUser(userEntity);
                return phoneEntity;
            }).toList());
        UserEntity userEntity1 = userRepository.save(userEntity);
        return mapper.toUser(userEntity1);
    }
}
