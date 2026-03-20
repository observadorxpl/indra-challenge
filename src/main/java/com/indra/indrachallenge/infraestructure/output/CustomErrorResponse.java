package com.indra.indrachallenge.infraestructure.output;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomErrorResponse {
    private String message;
}
