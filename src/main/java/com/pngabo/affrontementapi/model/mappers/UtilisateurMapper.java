package com.pngabo.affrontementapi.model.mappers;

import com.pngabo.affrontementapi.model.dtos.AdminDTO;
import com.pngabo.affrontementapi.model.dtos.AffrontementDTO;
import com.pngabo.affrontementapi.model.dtos.ArbitreDTO;
import com.pngabo.affrontementapi.model.dtos.JoueurDTO;
import com.pngabo.affrontementapi.model.entities.Admin;
import com.pngabo.affrontementapi.model.entities.Affrontement;
import com.pngabo.affrontementapi.model.entities.Arbitre;
import com.pngabo.affrontementapi.model.entities.Joueur;
import com.pngabo.affrontementapi.model.forms.AdminForm;
import com.pngabo.affrontementapi.model.forms.ArbitreForm;
import com.pngabo.affrontementapi.model.forms.JoueurForm;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UtilisateurMapper {
    private final AffrontementMapper aMapper;

    public UtilisateurMapper(AffrontementMapper aMapper) {
        this.aMapper = aMapper;
    }

    public AdminDTO adminToDTO(Admin admin) {
        return AdminDTO.builder()
                .id(admin.getId())
                .nom(admin.getNom())
                .prenom(admin.getPrenom())
                .username(admin.getUsername())
                .build();
    }

    public ArbitreDTO arbitreDTO(Arbitre arb) {
        return ArbitreDTO.builder()
                .id(arb.getId())
                .nom(arb.getNom())
                .prenom(arb.getPrenom())
                .username(arb.getUsername())
                .participations(arb.getParticipations().stream()
                        .map(aMapper::entityToDTO)
                        .collect(Collectors.toList()))
                .build();
    }

    public JoueurDTO joueurToDTO(Joueur j) {
        List<Affrontement> allAffrontements = new ArrayList<>();

        if(j.getParticipations1() != null)
            allAffrontements.addAll(j.getParticipations1());

        if (j.getParticipations2() != null)
            allAffrontements.addAll(j.getParticipations2());

        return JoueurDTO.builder()
                .id(j.getId())
                .nom(j.getNom())
                .prenom(j.getPrenom())
                .age(j.getAge())
                .username(j.getUsername())
                .ligues(j.getLigues().stream()
                        .map((e)->{
                            return JoueurDTO.LigueIntern.builder()
                                    .id(e.getId())
                                    .nom(e.getNom())
                                    .build();
                        })
                        .collect(Collectors.toList()))
                .allParticipations(allAffrontements.stream()
                                                    .map(aMapper::entityToDTO)
                                                    .collect(Collectors.toList()))
                .victoires(j.getVictoires().stream()
                                            .map(aMapper::entityToDTO)
                                            .collect(Collectors.toList()))
                .build();
    }

    public Admin formToEntity(AdminForm form) {

        Admin admin = new Admin();
        admin.setId(form.getId());
        admin.setNom(form.getNom());
        admin.setPrenom(form.getPrenom());
        admin.setUsername(form.getUsername());
        admin.setPassword(form.getPassword());

        return admin;
    }

    public Arbitre formToEntity(ArbitreForm form) {

        Arbitre arbitre = new Arbitre();
        arbitre.setId(form.getId());
        arbitre.setNom(form.getNom());
        arbitre.setPrenom(form.getPrenom());
        arbitre.setUsername(form.getUsername());
        arbitre.setPassword(form.getPassword());
        arbitre.setParticipations(form.getParticipations());

        return arbitre;
    }

    public Joueur formToEntity(JoueurForm form) {

        Joueur joueur = new Joueur();
        joueur.setId(form.getId());
        joueur.setNom(form.getNom());
        joueur.setPrenom(form.getPrenom());
        joueur.setAge(form.getAge());
        joueur.setUsername(form.getUsername());
        joueur.setPassword(form.getPassword());
        joueur.setLigues(form.getLigues());
        joueur.setParticipations1(form.getParticipations1());
        joueur.setParticipations2(form.getParticipations2());
        joueur.setVictoires(form.getVictoires());

        return joueur;
    }
}
