package com.pngabo.affrontementapi.model.forms;

import com.pngabo.affrontementapi.model.EtatAffrontement;
import com.pngabo.affrontementapi.model.entities.Arbitre;
import com.pngabo.affrontementapi.model.entities.Joueur;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
public class AffrontementForm {
    @NotNull
    private long id;
    @NotNull
    private LocalDate dateDebut;
    @NotNull
    private LocalDate dateFin;
    @NotNull
    private EtatAffrontement etat;
    private Joueur interv1;
    private Joueur interv2;
    private Joueur vainqueur;
    private Arbitre arbitre;
}
