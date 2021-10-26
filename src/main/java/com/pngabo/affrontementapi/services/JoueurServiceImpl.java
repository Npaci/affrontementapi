package com.pngabo.affrontementapi.services;

import com.pngabo.affrontementapi.exceptions.ElementAlreadyExistException;
import com.pngabo.affrontementapi.exceptions.ElementNotFoundException;
import com.pngabo.affrontementapi.model.EtatAffrontement;
import com.pngabo.affrontementapi.model.dtos.JoueurDTO;
import com.pngabo.affrontementapi.model.entities.Affrontement;
import com.pngabo.affrontementapi.model.entities.Joueur;
import com.pngabo.affrontementapi.model.entities.Ligue;
import com.pngabo.affrontementapi.model.forms.JoueurForm;
import com.pngabo.affrontementapi.model.mappers.UtilisateurMapper;
import com.pngabo.affrontementapi.repositories.AffrontementRepository;
import com.pngabo.affrontementapi.repositories.JoueurRepository;
import com.pngabo.affrontementapi.repositories.LigueRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JoueurServiceImpl implements JoueurService{
    private final JoueurRepository repository;
    private final UtilisateurMapper mapper;
    private  final AffrontementRepository affRepository;
    private final LigueRepository lRepository;

    public JoueurServiceImpl(JoueurRepository repository, UtilisateurMapper mapper, AffrontementRepository affRepository, LigueRepository lRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.affRepository = affRepository;
        this.lRepository = lRepository;
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
            List<String> roles = auth.getAuthorities().stream().map(elm -> elm.getAuthority()).collect(Collectors.toList());

            if((connectedUser != null && todelete.getUsername().equals(connectedUser)) || roles.contains("ADMIN")) {
                //supprimer les ref des différentes ligues
                if (todelete.getLigues() != null) {
                    for (Ligue lig : todelete.getLigues()) {
                        deleteRefinLigue(lig, aLong);
                    }
                }
                repository.delete(todelete);
                return mapper.joueurToDTO(todelete);
            }
            else
                throw new BadCredentialsException("Ce compte ne vous appartient pas, requête refusée!");
        }
        else
            throw new AuthenticationCredentialsNotFoundException("Connectez vous avant de pouvoir effectuer cette requête");
    }

    private void deleteRefinLigue(Ligue ligue, long idJ) {
        List<Joueur> joueurs = ligue.getJoueurs();
        for (int i = 0; i < joueurs.size(); i++) {
            if (idJ == joueurs.get(i).getId())
                joueurs.remove(i);
        }
        lRepository.save(ligue);
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
        boolean inscrit = false;
        if (!repository.existsById(idJoueur) && !lRepository.existsById(idLigue))
            throw new ElementNotFoundException();

        Joueur j = repository.findById(idJoueur)
                .orElseThrow(ElementNotFoundException::new);

        List<Affrontement> affList = affRepository.findByLigueId(idLigue);
        if (affList != null) {
            for (int i = 0; i < affList.size() && !inscrit; i++) {
                //si une place est encore disponible
                Affrontement aff = affList.get(i);
                if (aff.getJoueur1() == null || aff.getJoueur2() == null) {
                    System.out.println("Affrontement DISPONIBLE!!!");

                    if (aff.getJoueur1() == null)
                        aff.setJoueur1(j);
                    else if (aff.getJoueur2() == null)
                        aff.setJoueur2(j);

                    inscrit = true;

                    //Mettre à jour l'affrontement si complet
                    if (aff.getJoueur1() != null && aff.getJoueur2() != null && aff.getArbitre() != null)
                        aff.setEtat(EtatAffrontement.ENCOURS);

                    affRepository.save(aff);
                } else
                    System.out.println("Affrontement COMPLET!!!");
            }

            if (inscrit) {
                j = repository.findById(idJoueur)
                        .orElseThrow(ElementNotFoundException::new);
                return mapper.joueurToDTO(j);
            }

        }

        //Si pas d'affront de cette ligue ou si le joueur n'est pas inscrit -> Creer un nouvelle affrontement
        Ligue lig = lRepository.findById(idLigue)
                .orElseThrow(ElementNotFoundException::new);

        Affrontement aff = Affrontement.builder()
                .id(0L)
//                .dateDebut()
//                .dateFin()
                .etat(EtatAffrontement.RECHERCHE)
                .ligue(lig)
                .joueur1(j)
//                .joueur2()
//                .vainqueur()
//                .arbitre()
                .build();

        affRepository.save(aff);

        return mapper.joueurToDTO(j);

    }
}
