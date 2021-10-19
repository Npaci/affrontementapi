package com.pngabo.affrontementapi.services;

import com.pngabo.affrontementapi.exceptions.ElementAlreadyExistException;
import com.pngabo.affrontementapi.exceptions.ElementNotFoundException;
import com.pngabo.affrontementapi.model.dtos.JoueurDTO;
import com.pngabo.affrontementapi.model.entities.Joueur;
import com.pngabo.affrontementapi.model.forms.JoueurForm;
import com.pngabo.affrontementapi.model.mappers.UtilisateurMapper;
import com.pngabo.affrontementapi.repositories.JoueurRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JoueurServiceImpl implements JoueurService{
    private final JoueurRepository repository;
    private UtilisateurMapper mapper;

    public JoueurServiceImpl(JoueurRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<JoueurDTO> getAll() {
        return repository.findAll().stream()
                .map(mapper::joueurToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public JoueurDTO getOne(Long aLong) {
        if (!repository.existsById(aLong))
            throw new ElementNotFoundException();

        Joueur j = repository.findById(aLong)
                .orElseThrow(ElementNotFoundException::new);

        return mapper.joueurToDTO(j);
    }

    @Override
    public JoueurDTO insert(JoueurForm form) {
        if (repository.existsById(form.getId()))
            throw new ElementAlreadyExistException();

        Joueur j = mapper.formToEntity(form);

        return mapper.joueurToDTO(repository.save(j));
    }

    @Override
    public JoueurDTO delete(Long aLong) {
        if (!repository.existsById(aLong))
            throw new ElementNotFoundException();

        Joueur todelete = repository.findById(aLong)
                .orElseThrow(ElementNotFoundException::new);

        repository.delete(todelete);

        return mapper.joueurToDTO(todelete);
    }

    @Override
    public JoueurDTO update(JoueurForm form) {
        if (!repository.existsById(form.getId()))
            throw new ElementNotFoundException();

        Joueur toUpdate = mapper.formToEntity(form);

        return mapper.joueurToDTO(repository.save(toUpdate));
    }
}
