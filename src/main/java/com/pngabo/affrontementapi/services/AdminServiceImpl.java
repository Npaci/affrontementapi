package com.pngabo.affrontementapi.services;

import com.pngabo.affrontementapi.exceptions.ElementAlreadyExistException;
import com.pngabo.affrontementapi.exceptions.ElementNotFoundException;
import com.pngabo.affrontementapi.model.dtos.AdminDTO;
import com.pngabo.affrontementapi.model.entities.Admin;
import com.pngabo.affrontementapi.model.forms.AdminForm;
import com.pngabo.affrontementapi.model.mappers.UtilisateurMapper;
import com.pngabo.affrontementapi.repositories.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService{
    private final AdminRepository repository;
    private final UtilisateurMapper mapper;

    public AdminServiceImpl(AdminRepository repository, UtilisateurMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<AdminDTO> getAll() {
        return repository.findAll().stream()
                .map(mapper::adminToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AdminDTO getOne(Long aLong) {
        if (!repository.existsById(aLong))
            throw new ElementNotFoundException();

        Admin admin = repository.findById(aLong)
                .orElseThrow(ElementNotFoundException::new);

        return mapper.adminToDTO(admin);
    }

    @Override
    public AdminDTO insert(AdminForm form) {
        if (repository.existsById(form.getId()))
            throw new ElementAlreadyExistException();

        Admin admin = mapper.formToEntity(form);

        return mapper.adminToDTO(repository.save(admin));
    }

    @Override
    public AdminDTO delete(Long aLong) {
        if (!repository.existsById(aLong))
            throw new ElementNotFoundException();

        Admin todelete = repository.findById(aLong)
                .orElseThrow(ElementNotFoundException::new);

        repository.delete(todelete);

        return mapper.adminToDTO(todelete);
    }

    @Override
    public AdminDTO update(AdminForm form) {
        if (!repository.existsById(form.getId()))
            throw new ElementNotFoundException();

        Admin toUpdate = mapper.formToEntity(form);

        return mapper.adminToDTO(repository.save(toUpdate));
    }
}
