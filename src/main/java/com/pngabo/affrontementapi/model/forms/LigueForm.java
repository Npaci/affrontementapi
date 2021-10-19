package com.pngabo.affrontementapi.model.forms;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class LigueForm {
    @NotNull
    private long id;
    @NotBlank
    private String nom;
}
