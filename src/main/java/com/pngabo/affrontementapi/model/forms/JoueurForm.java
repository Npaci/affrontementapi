package com.pngabo.affrontementapi.model.forms;

import com.pngabo.affrontementapi.model.entities.Ligue;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
public class JoueurForm {
    @NotNull
    private long id;
    @NotBlank
    private String nom;
    @NotBlank
    private String prenom;
    @NotNull
    private int age;
    @NotNull
    private List<Ligue> ligues;
}
