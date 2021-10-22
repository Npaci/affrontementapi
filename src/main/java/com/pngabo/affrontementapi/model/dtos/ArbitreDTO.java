package com.pngabo.affrontementapi.model.dtos;

import com.pngabo.affrontementapi.model.EtatAffrontement;
import com.pngabo.affrontementapi.model.entities.Affrontement;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class ArbitreDTO {
    private long id;
    private String nom;
    private String prenom;
    private String username;
    private List<AffrontementDTO> participations;
}
