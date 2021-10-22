package com.pngabo.affrontementapi.model.mappers;

import com.pngabo.affrontementapi.model.dtos.AffrontementDTO;
import com.pngabo.affrontementapi.model.entities.Affrontement;
import com.pngabo.affrontementapi.model.entities.Arbitre;
import com.pngabo.affrontementapi.model.entities.Joueur;
import com.pngabo.affrontementapi.model.entities.Ligue;
import com.pngabo.affrontementapi.model.forms.AffrontementForm;
import org.springframework.stereotype.Component;

@Component
public class AffrontementMapper implements Mapper<Affrontement, AffrontementDTO, AffrontementForm>{
    @Override
    public AffrontementDTO entityToDTO(Affrontement aff) {
        if (aff == null)
            return null;

        return AffrontementDTO.builder()
                .id(aff.getId())
                .dateDebut(aff.getDateDebut())
                .dateFin(aff.getDateFin())
                .arbitre(arbitreIntern(aff.getArbitre()))
                .ligue(ligueIntern(aff.getLigue()))
                .interv1(joueurIntern(aff.getJoueur1()))
                .interv2(joueurIntern(aff.getJoueur2()))
                .vainqueur(joueurIntern(aff.getVainqueur()))
                .etat(aff.getEtat())
                .build();
    }

    private AffrontementDTO.LigueIntern ligueIntern(Ligue ligue) {
        if (ligue == null)
            return null;

        return AffrontementDTO.LigueIntern.builder()
                .id(ligue.getId())
                .nom(ligue.getNom())
                .build();
    }

    private AffrontementDTO.ArbitreIntern arbitreIntern(Arbitre arbitre) {
        if (arbitre == null)
            return null;

        return AffrontementDTO.ArbitreIntern.builder()
                .id(arbitre.getId())
                .nom(arbitre.getNom())
                .prenom(arbitre.getPrenom())
                .build();
    }

    private AffrontementDTO.JoueurIntern joueurIntern(Joueur j) {
        if (j == null)
            return null;

        return AffrontementDTO.JoueurIntern.builder()
                .id(j.getId())
                .nom(j.getNom())
                .prenom(j.getPrenom())
                .age(j.getAge())
                .build();
    }

    @Override
    public Affrontement formToEntity(AffrontementForm form) {
        if (form == null)
            return null;

        return Affrontement.builder()
                .id(form.getId())
                .dateDebut(form.getDateDebut())
                .dateFin(form.getDateFin())
                .etat(form.getEtat())
                .ligue(form.getLigue())
                .arbitre(form.getArbitre())
                .joueur1(form.getInterv1())
                .joueur2(form.getInterv2())
                .vainqueur(form.getVainqueur())
                .build();
    }
}
