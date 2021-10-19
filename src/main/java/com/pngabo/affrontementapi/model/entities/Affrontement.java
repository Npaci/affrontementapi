package com.pngabo.affrontementapi.model.entities;

import com.pngabo.affrontementapi.model.EtatAffrontement;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

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
    private LocalDate dateDebut;
    private LocalDate dateFin;
    @Enumerated(EnumType.STRING)
    private EtatAffrontement etat;

    @ManyToOne
    private Joueur joueur1;

    @ManyToOne
    private Joueur joueur2;

    @ManyToOne
    private Joueur vainqueur;

    @ManyToOne
    private Arbitre arbitre;
}
