package com.pngabo.affrontementapi.model.dtos;

import com.pngabo.affrontementapi.model.EtatAffrontement;
import com.pngabo.affrontementapi.model.entities.Arbitre;
import com.pngabo.affrontementapi.model.entities.Joueur;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class AffrontementDTO {
    private long id;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private EtatAffrontement etat;
    private Joueur interv1;
    private Joueur interv2;
    private Joueur vainqueur;
    private Arbitre arbitre;
}
