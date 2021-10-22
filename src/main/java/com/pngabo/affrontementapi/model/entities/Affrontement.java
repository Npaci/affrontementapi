package com.pngabo.affrontementapi.model.entities;

import com.pngabo.affrontementapi.model.EtatAffrontement;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Affrontement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    @Enumerated(EnumType.STRING)
    private EtatAffrontement etat;

    @ManyToOne
    private Ligue ligue;

    @ManyToOne
    private Joueur joueur1;

    @ManyToOne
    private Joueur joueur2;

    @ManyToOne
    private Joueur vainqueur;

    @ManyToOne
    private Arbitre arbitre;
}
