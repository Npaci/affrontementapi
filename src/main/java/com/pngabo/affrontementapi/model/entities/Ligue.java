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
    @Column(nullable = false)
    private String nom;

    @ManyToMany(mappedBy = "ligues")
    private List<Joueur> joueurs;
}
