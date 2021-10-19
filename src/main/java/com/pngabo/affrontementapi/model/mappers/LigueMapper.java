package com.pngabo.affrontementapi.model.mappers;

import com.pngabo.affrontementapi.model.dtos.LigueDTO;
import com.pngabo.affrontementapi.model.entities.Ligue;
import com.pngabo.affrontementapi.model.forms.LigueForm;
import org.springframework.stereotype.Component;

@Component
public class LigueMapper implements Mapper<Ligue, LigueDTO, LigueForm>{
    @Override
    public LigueDTO entityToDTO(Ligue ligue) {
        return LigueDTO.builder()
                .id(ligue.getId())
                .nom(ligue.getNom())
                .build();
    }

    @Override
    public Ligue formToEntity(LigueForm form) {
        return null;
    }
}
