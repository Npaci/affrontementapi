package com.pngabo.affrontementapi.model.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("JOUEUR")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Joueur extends Utilisateur {
    @Column(nullable = false)
    private int age;
    @Column(nullable = false)

    @ManyToMany
    private List<Ligue> ligues;

    @OneToMany(mappedBy = "joueur1")
    private List<Affrontement> participations1;

    @OneToMany(mappedBy = "joueur2")
    private List<Affrontement> participations2;

    @OneToMany(mappedBy = "vainqueur")
    private List<Affrontement> victoires;
}
