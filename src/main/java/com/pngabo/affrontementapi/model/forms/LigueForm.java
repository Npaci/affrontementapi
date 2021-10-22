package com.pngabo.affrontementapi.model.forms;

import com.pngabo.affrontementapi.model.dtos.AffrontementDTO;
import com.pngabo.affrontementapi.model.dtos.JoueurDTO;
import com.pngabo.affrontementapi.model.entities.Affrontement;
import com.pngabo.affrontementapi.model.entities.Joueur;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@Validated
public class LigueForm {
    @NotNull
    private long id;
    @NotBlank
    private String nom;
    private List<Joueur> joueurs;
    private List<Affrontement> affrontements;
}
