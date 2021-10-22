package com.pngabo.affrontementapi.services;

import com.pngabo.affrontementapi.exceptions.ElementAlreadyExistException;
import com.pngabo.affrontementapi.exceptions.ElementNotFoundException;
import com.pngabo.affrontementapi.model.dtos.ArbitreDTO;
import com.pngabo.affrontementapi.model.entities.Arbitre;
import com.pngabo.affrontementapi.model.forms.ArbitreForm;
import com.pngabo.affrontementapi.model.mappers.UtilisateurMapper;
import com.pngabo.affrontementapi.repositories.ArbitreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArbitreServiceImpl implements ArbitreService {
    private final ArbitreRepository repository;
    private final UtilisateurMapper mapper;

    public ArbitreServiceImpl(ArbitreRepository repository, UtilisateurMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<ArbitreDTO> getAll() {
        return repository.findAll().stream()
                .map(mapper::arbitreDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ArbitreDTO getOne(Long aLong) {
        if (!repository.existsById(aLong))
            throw new ElementNotFoundException();

        Arbitre found = repository.findById(aLong)
                .orElseThrow(ElementNotFoundException::new);

        return mapper.arbitreDTO(found);
    }

    @Override
    public ArbitreDTO insert(ArbitreForm form) {
        if (repository.existsById(form.getId()))
            throw new ElementAlreadyExistException();

        Arbitre toInsert = mapper.formToEntity(form);

        return mapper.arbitreDTO(repository.save(toInsert));
    }

    @Transactional
    @Override
    public ArbitreDTO delete(Long aLong) {
        if (!repository.existsById(aLong))
            throw new ElementNotFoundException();

        Arbitre todelete = repository.findById(aLong)
                .orElseThrow(ElementNotFoundException::new);

        repository.delete(todelete);

        return mapper.arbitreDTO(todelete);
    }

    @Override
    public ArbitreDTO update(ArbitreForm form) {
        if (!repository.existsById(form.getId()))
            throw new ElementNotFoundException();

        Arbitre toUpdate = mapper.formToEntity(form);

        return mapper.arbitreDTO(repository.save(toUpdate));
    }
}
