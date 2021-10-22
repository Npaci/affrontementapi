package com.pngabo.affrontementapi.model.entities;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Ligue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, unique = true)
    private String nom;

    @ManyToMany(mappedBy = "ligues")
    private List<Joueur> joueurs;

    @OneToMany(mappedBy = "ligue")//A confirmer par Alex
    private List<Affrontement> affrontements;
}
