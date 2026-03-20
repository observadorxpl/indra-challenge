package com.indra.indrachallenge.infraestructure.output.persistence.mapper;

import com.indra.indrachallenge.domain.Phone;
import com.indra.indrachallenge.infraestructure.output.persistence.entity.PhoneEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PhonePersistenceMapper {
    @Mapping(target = "number", source = "number")
    @Mapping(target = "cityCode", source = "cityCode")
    @Mapping(target = "countryCode", source = "countryCode")
    PhoneEntity toPhoneEntity(Phone phone);
}
