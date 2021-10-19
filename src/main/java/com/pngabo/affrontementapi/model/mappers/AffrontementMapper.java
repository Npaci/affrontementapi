package com.pngabo.affrontementapi.model.mappers;

import com.pngabo.affrontementapi.model.dtos.AffrontementDTO;
import com.pngabo.affrontementapi.model.entities.Affrontement;
import com.pngabo.affrontementapi.model.forms.AffrontementForm;
import org.springframework.stereotype.Component;

@Component
public class AffrontementMapper implements Mapper<Affrontement, AffrontementDTO, AffrontementForm>{
    @Override
    public AffrontementDTO entityToDTO(Affrontement aff) {
        return AffrontementDTO.builder()
                .id(aff.getId())
                .dateDebut(aff.getDateDebut())
                .dateFin(aff.getDateFin())
                .arbitre(aff.getArbitre())
                .interv1(aff.getInterv1())
                .interv2(aff.getInterv2())
                .vainqueur(aff.getVainqueur())
                .etat(aff.getEtat())
                .build();
    }

    @Override
    public Affrontement formToEntity(AffrontementForm form) {
        return Affrontement.builder()
                .id(form.getId())
                .dateDebut(form.getDateDebut())
                .dateFin(form.getDateFin())
                .arbitre(form.getArbitre())
                .interv1(form.getInterv1())
                .interv2(form.getInterv2())
                .vainqueur(form.getVainqueur())
                .etat(form.getEtat())
                .build();
    }
}
