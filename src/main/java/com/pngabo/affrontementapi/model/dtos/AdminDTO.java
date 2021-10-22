package com.pngabo.affrontementapi.model.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminDTO {
    private long id;
    private String nom;
    private String prenom;
    private String username;
}
