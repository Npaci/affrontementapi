package com.pngabo.affrontementapi.model.forms;

import com.pngabo.affrontementapi.model.entities.Affrontement;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@Validated
public class ArbitreForm {
    @NotNull
    private long id;
    @NotBlank
    private String nom;
    @NotBlank
    private String prenom;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    private List<Affrontement> participations;
}
