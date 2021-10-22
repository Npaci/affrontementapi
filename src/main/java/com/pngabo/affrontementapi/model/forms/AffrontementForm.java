package com.pngabo.affrontementapi.model.forms;

import com.pngabo.affrontementapi.model.EtatAffrontement;
import com.pngabo.affrontementapi.model.entities.Arbitre;
import com.pngabo.affrontementapi.model.entities.Joueur;
import com.pngabo.affrontementapi.model.entities.Ligue;
import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@Validated
public class AffrontementForm {
    @NotNull
    private long id;
    @NotNull
    private LocalDateTime dateDebut;
    @NotNull
    private LocalDateTime dateFin;
    @NotNull
    private EtatAffrontement etat;
    private Ligue ligue;
    private Joueur interv1;
    private Joueur interv2;
    private Joueur vainqueur;
    private Arbitre arbitre;
}
