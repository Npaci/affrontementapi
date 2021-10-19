package com.pngabo.affrontementapi.services;

import com.pngabo.affrontementapi.exceptions.ElementAlreadyExistException;
import com.pngabo.affrontementapi.exceptions.ElementNotFoundException;
import com.pngabo.affrontementapi.model.dtos.AffrontementDTO;
import com.pngabo.affrontementapi.model.entities.Affrontement;
import com.pngabo.affrontementapi.model.forms.AffrontementForm;
import com.pngabo.affrontementapi.model.mappers.AffrontementMapper;
import com.pngabo.affrontementapi.repositories.AffrontementRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AffrontementServiceImpl implements AffrontementService{
    private final AffrontementRepository repository;
    private final AffrontementMapper mapper;

    public AffrontementServiceImpl(AffrontementRepository repository, AffrontementMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<AffrontementDTO> getAll() {
        return repository.findAll().stream()
                .map(mapper::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AffrontementDTO getOne(Long aLong) {
        if (!repository.existsById(aLong))
            throw new ElementNotFoundException();

        Affrontement found = repository.findById(aLong)
                .orElseThrow(ElementNotFoundException::new);

        return mapper.entityToDTO(found);
    }

    @Override
    public AffrontementDTO insert(AffrontementForm form) {
        if (repository.existsById(form.getId()))
            throw new ElementAlreadyExistException();

        Affrontement toInsert = mapper.formToEntity(form);

        return mapper.entityToDTO(repository.save(toInsert));
    }

    @Override
    public AffrontementDTO delete(Long aLong) {
        if (!repository.existsById(aLong))
            throw new ElementNotFoundException();

        Affrontement todelete = repository.findById(aLong)
                .orElseThrow(ElementNotFoundException::new);

        repository.delete(todelete);

        return mapper.entityToDTO(todelete);
    }

    @Override
    public AffrontementDTO update(AffrontementForm form) {
        if (!repository.existsById(form.getId()))
            throw new ElementNotFoundException();

        Affrontement toUpdate = mapper.formToEntity(form);

        return mapper.entityToDTO(repository.save(toUpdate));
    }
}
