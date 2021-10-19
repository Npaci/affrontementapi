package com.pngabo.affrontementapi.model.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="user_type")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public abstract class Utilisateur {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    protected Long id;
    @Column(nullable = false)
    protected String nom;
    @Column(nullable = false)
    protected String prenom;
}