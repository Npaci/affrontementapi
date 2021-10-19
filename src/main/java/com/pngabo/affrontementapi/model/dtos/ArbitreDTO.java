package com.pngabo.affrontementapi.model.dtos;

import lombok.*;

@Data
@Builder
public class ArbitreDTO {
    private long id;
    private String nom;
    private String prenom;
}
