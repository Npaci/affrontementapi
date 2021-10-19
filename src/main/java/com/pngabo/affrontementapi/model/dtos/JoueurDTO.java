package com.pngabo.affrontementapi.model.dtos;

import com.pngabo.affrontementapi.model.entities.Ligue;
import com.pngabo.affrontementapi.model.entities.Utilisateur;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Data
@Builder
public class JoueurDTO {
    private long id;
    private String nom;
    private String prenom;
    private int age;
    private List<Ligue> ligues;
}
