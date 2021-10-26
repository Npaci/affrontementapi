package com.pngabo.affrontementapi.model.entities;

import lombok.*;
import org.hibernate.action.internal.OrphanRemovalAction;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("JOUEUR")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Joueur extends Utilisateur {
    private int age;

    @ManyToMany
    private List<Ligue> ligues;

    @OneToMany(mappedBy = "joueur1", cascade = CascadeType.REMOVE)
    private List<Affrontement> participations1;

    @OneToMany(mappedBy = "joueur2", cascade = CascadeType.REMOVE)
    private List<Affrontement> participations2;

    @OneToMany(mappedBy = "vainqueur", cascade = CascadeType.REMOVE)
//    @LazyCollection(value = LazyCollectionOption.FALSE)
    private List<Affrontement> victoires;
}
