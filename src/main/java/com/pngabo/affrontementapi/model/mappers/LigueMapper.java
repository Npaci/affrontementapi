package com.pngabo.affrontementapi.model.mappers;

import com.pngabo.affrontementapi.model.dtos.LigueDTO;
import com.pngabo.affrontementapi.model.entities.Ligue;
import com.pngabo.affrontementapi.model.forms.LigueForm;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class LigueMapper implements Mapper<Ligue, LigueDTO, LigueForm>{
    private final UtilisateurMapper mapper;
    private final AffrontementMapper affMapper;

    public LigueMapper(UtilisateurMapper mapper, AffrontementMapper affMapper) {
        this.mapper = mapper;
        this.affMapper = affMapper;
    }

    @Override
    public LigueDTO entityToDTO(Ligue ligue) {
        return LigueDTO.builder()
                .id(ligue.getId())
                .nom(ligue.getNom())
                .joueurs(ligue.getJoueurs().stream()
                        .map(mapper::joueurToDTO)
                        .collect(Collectors.toList()))
                .affrontements(ligue.getAffrontements().stream()
                        .map(affMapper::entityToDTO)
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public Ligue formToEntity(LigueForm form) {
        return Ligue.builder()
                .id(form.getId())
                .nom(form.getNom())
                .joueurs(form.getJoueurs())
                .affrontements(form.getAffrontements())
                .build();
    }
}
