package com.pngabo.affrontementapi.model.dtos;

import com.pngabo.affrontementapi.model.entities.Affrontement;
import com.pngabo.affrontementapi.model.entities.Joueur;
import lombok.*;

import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Builder
public class LigueDTO {
    private long id;
    private String nom;
    private List<JoueurDTO> joueurs;
    private List<AffrontementDTO> affrontements;
}
