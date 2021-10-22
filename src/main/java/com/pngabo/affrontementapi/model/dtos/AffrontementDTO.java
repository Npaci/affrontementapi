package com.pngabo.affrontementapi.model.dtos;

import com.pngabo.affrontementapi.model.EtatAffrontement;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AffrontementDTO {
    private long id;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private EtatAffrontement etat;
    private LigueIntern ligue;
    private JoueurIntern interv1;
    private JoueurIntern interv2;
    private JoueurIntern vainqueur;
    private ArbitreIntern arbitre;

    @Data
    @Builder
    public static class LigueIntern {
        private long id;
        private String nom;
    }

    @Data
    @Builder
    public static class JoueurIntern {
        private long id;
        private String nom;
        private String prenom;
        private int age;
    }

    @Data
    @Builder
    public static class ArbitreIntern {
        private long id;
        private String nom;
        private String prenom;
    }
}
