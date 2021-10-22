package com.pngabo.affrontementapi.services;

import com.pngabo.affrontementapi.exceptions.ElementAlreadyExistException;
import com.pngabo.affrontementapi.exceptions.ElementNotFoundException;
import com.pngabo.affrontementapi.model.EtatAffrontement;
import com.pngabo.affrontementapi.model.dtos.JoueurDTO;
import com.pngabo.affrontementapi.model.entities.Affrontement;
import com.pngabo.affrontementapi.model.entities.Joueur;
import com.pngabo.affrontementapi.model.entities.Ligue;
import com.pngabo.affrontementapi.model.entities.Utilisateur;
import com.pngabo.affrontementapi.model.forms.JoueurForm;
import com.pngabo.affrontementapi.model.mappers.UtilisateurMapper;
import com.pngabo.affrontementapi.repositories.AffrontementRepository;
import com.pngabo.affrontementapi.repositories.JoueurRepository;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JoueurServiceImpl implements JoueurService{
    private final JoueurRepository repository;
    private final UtilisateurMapper mapper;
    private  final AffrontementRepository affRepository;

    public JoueurServiceImpl(JoueurRepository repository, UtilisateurMapper mapper, AffrontementRepository affRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.affRepository = affRepository;
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
        j = repository.save(j);

        return mapper.joueurToDTO(j);
    }

    @Transactional
    public JoueurDTO deleteSecure(Long aLong, Authentication auth) {
        if (auth != null){
            if (!repository.existsById(aLong))
                throw new ElementNotFoundException();

            Joueur todelete = repository.findById(aLong)
                    .orElseThrow(ElementNotFoundException::new);

            Object connectedUser = auth.getPrincipal();

            if(todelete.getUsername().equals(connectedUser)) {
                repository.delete(todelete);
                return mapper.joueurToDTO(todelete);
            }
            else
                throw new BadCredentialsException("Vous n'êtes pas authorisé à effectuer cette requête!");
        }
        else
            throw new AuthenticationCredentialsNotFoundException("Connectez vous avant de pouvoir effectuer cette requête");
    }

    @Transactional
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


    @Override
    public JoueurDTO inscriptionAffrontement(Long idJoueur, Long idLigue) {
        if (!repository.existsById(idJoueur))
            throw new ElementNotFoundException();

        Joueur j = repository.findById(idJoueur)
                .orElseThrow(ElementNotFoundException::new);

        Affrontement aff = affRepository.findByLigueId(idLigue);
        //si une place est encore disponible
        if (aff.getEtat() != EtatAffrontement.TERMINE) {
            System.out.println("Affrontement DISPONIBLE!!!");
            if (aff.getJoueur1() == null) {
                aff.setJoueur1(j);
                affRepository.save(aff);
            }
            else if (aff.getJoueur2() == null) {
                aff.setJoueur2(j);
                affRepository.save(aff);
            }

            //Mettre à jour l'affrontement si complet
            if (aff.getJoueur1() != null && aff.getJoueur2() != null)
                aff.setEtat(EtatAffrontement.ENCOURS);
        }
        else
            System.out.println("Affrontement COMPLET!!!");

        j = repository.findById(idJoueur)
                .orElseThrow(ElementNotFoundException::new);

        return mapper.joueurToDTO(j);
    }
}
