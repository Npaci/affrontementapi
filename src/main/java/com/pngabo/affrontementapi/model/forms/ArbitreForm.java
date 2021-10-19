package com.pngabo.affrontementapi.model.forms;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class ArbitreForm {
    @NotBlank
    private long id;
    @NotBlank
    private String nom;
    @NotBlank
    private String prenom;
}
