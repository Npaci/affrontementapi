package com.pngabo.affrontementapi.services;

import com.pngabo.affrontementapi.exceptions.ElementAlreadyExistException;
import com.pngabo.affrontementapi.exceptions.ElementNotFoundException;
import com.pngabo.affrontementapi.model.EtatAffrontement;
import com.pngabo.affrontementapi.model.dtos.ArbitreDTO;
import com.pngabo.affrontementapi.model.entities.Affrontement;
import com.pngabo.affrontementapi.model.entities.Arbitre;
import com.pngabo.affrontementapi.model.forms.ArbitreForm;
import com.pngabo.affrontementapi.model.mappers.UtilisateurMapper;
import com.pngabo.affrontementapi.repositories.AffrontementRepository;
import com.pngabo.affrontementapi.repositories.ArbitreRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArbitreServiceImpl implements ArbitreService {
    private final AffrontementRepository affRepository;
    private final ArbitreRepository repository;
    private final UtilisateurMapper mapper;

    public ArbitreServiceImpl(AffrontementRepository affRepository, ArbitreRepository repository, UtilisateurMapper mapper) {
        this.affRepository = affRepository;
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

    @Override
    public ArbitreDTO inscriptionAffrontement(Long idArbitre, Long idLigue) {
        boolean inscrit = false;
        if (!repository.existsById(idArbitre))
            throw new ElementNotFoundException();

        Arbitre arbitre = repository.findById(idArbitre)
                .orElseThrow(ElementNotFoundException::new);

        List<Affrontement> affList = affRepository.findByLigueId(idLigue);
        if (affList != null) {
            for (int i = 0; i < affList.size() && !inscrit; i++) {
                //si une place est encore disponible
                Affrontement aff = affList.get(i);
                if (aff.getArbitre() == null) {
                    System.out.println("Affrontement DISPONIBLE pour ARBITRE!!!");

                    aff.setArbitre(arbitre);

                    //Mettre à jour l'affrontement si complet
                    if (aff.getJoueur1() != null && aff.getJoueur2() != null && aff.getArbitre() != null)
                        aff.setEtat(EtatAffrontement.ENCOURS);

                    affRepository.save(aff);
                    inscrit = true;
                } else
                    System.out.println("Affrontement COMPLET pour ARBITRE!!!");
            }

            if (inscrit) {
                arbitre = repository.findById(idArbitre)
                        .orElseThrow(ElementNotFoundException::new);
                return mapper.arbitreDTO(arbitre);
            }

        }

        return null;
    }

    public ArbitreDTO arbitrerAffrontement(Long idArbitre, Long idAffront, boolean j1gagne) {

        if (!repository.existsById(idArbitre) || !affRepository.existsById(idAffront))
            throw new ElementNotFoundException();

        Affrontement affront = affRepository.findById(idAffront)
                .orElseThrow(ElementNotFoundException::new);

        if (affront.getArbitre() != null && affront.getArbitre().getId() == idArbitre && affront.getEtat() == EtatAffrontement.ENCOURS) {
            affront.setDateDebut(LocalDateTime.now());
            affront.setDateFin(LocalDateTime.now().plusMinutes(10L));

            if (j1gagne)
                affront.setVainqueur(affront.getJoueur1());
            else
                affront.setVainqueur(affront.getJoueur2());

            affront.setEtat(EtatAffrontement.TERMINE);
            affRepository.save(affront);

            Arbitre arbitre = repository.findById(idArbitre)
                    .orElseThrow(ElementNotFoundException::new);

            return mapper.arbitreDTO(arbitre);
        }

        return null;
    }
}
