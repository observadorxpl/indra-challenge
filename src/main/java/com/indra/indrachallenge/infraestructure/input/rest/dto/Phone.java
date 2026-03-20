package com.indra.indrachallenge.infraestructure.input.rest.dto;

public record Phone(String number, String cityCode, String countryCode) {

    com.indra.indrachallenge.domain.Phone toPhone(){
        return com.indra.indrachallenge.domain.Phone.builder()
            .number(this.number)
            .cityCode(this.cityCode)
            .countryCode(this.countryCode)
            .build();
    }
}
