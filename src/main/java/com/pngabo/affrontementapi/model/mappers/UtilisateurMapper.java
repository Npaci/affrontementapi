package com.pngabo.affrontementapi.model.mappers;

import com.pngabo.affrontementapi.model.dtos.AdminDTO;
import com.pngabo.affrontementapi.model.dtos.ArbitreDTO;
import com.pngabo.affrontementapi.model.dtos.JoueurDTO;
import com.pngabo.affrontementapi.model.entities.Admin;
import com.pngabo.affrontementapi.model.entities.Arbitre;
import com.pngabo.affrontementapi.model.entities.Joueur;
import com.pngabo.affrontementapi.model.forms.AdminForm;
import com.pngabo.affrontementapi.model.forms.ArbitreForm;
import com.pngabo.affrontementapi.model.forms.JoueurForm;
import org.springframework.stereotype.Component;

@Component
public class UtilisateurMapper {
    public AdminDTO adminToDTO(Admin admin) {
        return AdminDTO.builder()
                .id(admin.getId())
                .nom(admin.getNom())
                .prenom(admin.getPrenom())
                .build();
    }

    public ArbitreDTO arbitreDTO(Arbitre arb) {
        return ArbitreDTO.builder()
                .id(arb.getId())
                .nom(arb.getNom())
                .prenom(arb.getPrenom())
                .build();
    }

    public JoueurDTO joueurToDTO(Joueur j) {
        return JoueurDTO.builder()
                .id(j.getId())
                .nom(j.getNom())
                .prenom(j.getPrenom())
                .age(j.getAge())
                .ligues(j.getLigues())
                .build();
    }

    public Admin formToEntity(AdminForm form) {

        Admin admin = new Admin();
        admin.setId(form.getId());
        admin.setNom(form.getNom());
        admin.setPrenom(form.getPrenom());

        return admin;
    }

    public Arbitre formToEntity(ArbitreForm form) {

        Arbitre arbitre = new Arbitre();
        arbitre.setId(form.getId());
        arbitre.setNom(form.getNom());
        arbitre.setPrenom(form.getPrenom());

        return arbitre;
    }

    public Joueur formToEntity(JoueurForm form) {

        Joueur joueur = new Joueur();
        joueur.setId(form.getId());
        joueur.setNom(form.getNom());
        joueur.setPrenom(form.getPrenom());
        joueur.setAge(form.getAge());

        return joueur;
    }
}
