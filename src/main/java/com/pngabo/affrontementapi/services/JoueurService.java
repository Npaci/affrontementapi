package com.pngabo.affrontementapi.services;

import com.pngabo.affrontementapi.model.dtos.JoueurDTO;
import com.pngabo.affrontementapi.model.entities.Affrontement;
import com.pngabo.affrontementapi.model.forms.JoueurForm;
import org.springframework.data.jpa.repository.Query;

public interface JoueurService extends serviceCRUD<JoueurDTO, JoueurForm, Long> {
    JoueurDTO inscriptionAffrontement(Long idJoueur, Long idLigue);
}
