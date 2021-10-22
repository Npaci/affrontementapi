package com.pngabo.affrontementapi.model.forms;

import com.pngabo.affrontementapi.model.entities.Affrontement;
import com.pngabo.affrontementapi.model.entities.Ligue;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@Validated
public class JoueurForm {
    @NotNull
    private long id;
    @NotBlank
    private String nom;
    @NotBlank
    private String prenom;
    @NotNull
    private int age;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotNull
    private List<Ligue> ligues;
    private List<Affrontement> participations1;
    private List<Affrontement> participations2;
    private List<Affrontement> victoires;
}
