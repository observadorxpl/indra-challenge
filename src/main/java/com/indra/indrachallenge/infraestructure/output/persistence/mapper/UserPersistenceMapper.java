package com.indra.indrachallenge.infraestructure.output.persistence.mapper;

import com.indra.indrachallenge.domain.User;
import com.indra.indrachallenge.infraestructure.output.persistence.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", imports = { java.util.UUID.class })
public interface UserPersistenceMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "dateCreated", source = "dateCreated")
    @Mapping(target = "dateModified", source = "dateModified")
    @Mapping(target = "lastLogin", source = "lastLogin")
    @Mapping(target = "active", source = "active")
    UserEntity toUserEntity(User user);


    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "password", source = "password")
    @Mapping(target = "dateCreated", source = "dateCreated")
    @Mapping(target = "dateModified", source = "dateModified")
    @Mapping(target = "lastLogin", source = "lastLogin")
    @Mapping(target = "active", source = "active")
    User toUser(UserEntity entity);
}
