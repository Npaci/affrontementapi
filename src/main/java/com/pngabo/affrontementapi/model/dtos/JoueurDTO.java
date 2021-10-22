package com.pngabo.affrontementapi.model.dtos;

import com.pngabo.affrontementapi.model.entities.Affrontement;
import com.pngabo.affrontementapi.model.entities.Ligue;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class JoueurDTO {
    private long id;
    private String nom;
    private String prenom;
    private int age;
    private String username;
    private List<LigueIntern> ligues;
    private List<AffrontementDTO> allParticipations;
    private List<AffrontementDTO> victoires;

    @Data
    @Builder
    public static class LigueIntern {
        private long id;
        private String nom;
    }
}
