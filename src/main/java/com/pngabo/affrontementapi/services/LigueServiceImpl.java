package com.pngabo.affrontementapi.services;

import com.pngabo.affrontementapi.exceptions.ElementAlreadyExistException;
import com.pngabo.affrontementapi.exceptions.ElementNotFoundException;
import com.pngabo.affrontementapi.model.dtos.LigueDTO;
import com.pngabo.affrontementapi.model.entities.Ligue;
import com.pngabo.affrontementapi.model.forms.LigueForm;
import com.pngabo.affrontementapi.model.mappers.LigueMapper;
import com.pngabo.affrontementapi.repositories.LigueRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LigueServiceImpl implements LigueService{
    private final LigueRepository repository;
    private final LigueMapper mapper;

    public LigueServiceImpl(LigueRepository repository, LigueMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<LigueDTO> getAll() {
        return repository.findAll().stream()
                .map(mapper::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public LigueDTO getOne(Long aLong) {
        if (!repository.existsById(aLong))
            throw new ElementNotFoundException();

        Ligue found = repository.findById(aLong)
                .orElseThrow(ElementNotFoundException::new);

        return mapper.entityToDTO(found);
    }

    @Override
    public LigueDTO insert(LigueForm form) {
        if (repository.existsById(form.getId()))
            throw new ElementAlreadyExistException();

        Ligue toInsert = mapper.formToEntity(form);

        return mapper.entityToDTO(repository.save(toInsert));
    }

    @Override
    public LigueDTO delete(Long aLong) {
        if (!repository.existsById(aLong))
            throw new ElementNotFoundException();

        Ligue todelete = repository.findById(aLong)
                .orElseThrow(ElementNotFoundException::new);

        repository.delete(todelete);

        return mapper.entityToDTO(todelete);
    }

    @Override
    public LigueDTO update(LigueForm form) {
        if (!repository.existsById(form.getId()))
            throw new ElementNotFoundException();

        Ligue toUpdate = mapper.formToEntity(form);

        return mapper.entityToDTO(repository.save(toUpdate));
    }
}
