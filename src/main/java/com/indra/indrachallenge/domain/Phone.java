package com.indra.indrachallenge.domain;

import lombok.Builder;

@Builder
public record Phone(String number, String cityCode, String countryCode) {

}

