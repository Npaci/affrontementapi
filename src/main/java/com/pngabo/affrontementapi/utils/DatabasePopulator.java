package com.pngabo.affrontementapi.utils;

import com.pngabo.affrontementapi.model.EtatAffrontement;
import com.pngabo.affrontementapi.model.entities.*;
import com.pngabo.affrontementapi.repositories.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class DatabasePopulator implements InitializingBean {
    private final PasswordEncoder encoder;
    private final JoueurRepository jRepository;
    private final AdminRepository adminRepository;
    private final ArbitreRepository aRepository;
    private final LigueRepository lRepository;
    private final AffrontementRepository affrontementRepository;

    public DatabasePopulator(PasswordEncoder encoder, JoueurRepository jRepository, AdminRepository adminRepository, ArbitreRepository aRepository, LigueRepository lRepository, AffrontementRepository affrontementRepository) {
        this.encoder = encoder;
        this.jRepository = jRepository;
        this.adminRepository = adminRepository;
        this.aRepository = aRepository;
        this.lRepository = lRepository;
        this.affrontementRepository = affrontementRepository;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        //region ADMIN
        Admin ad1 = new Admin();
        ad1.setId(0L);
        ad1.setNom("Peper");
        ad1.setPrenom("Laurent");
        ad1.setUsername("Admin");
        ad1.setPassword(encoder.encode("pass"));
        ad1.setRoles(List.of("ADMIN","USER"));
        adminRepository.save(ad1);
        //endregion

        //region ARBITRE
        Arbitre ar1 = new Arbitre();
        ar1.setId(0L);
        ar1.setNom("Hurbort");
        ar1.setPrenom("Paul");
        ar1.setUsername("Hurb");
        ar1.setPassword(encoder.encode("pass"));
        ar1.setRoles(List.of("USER"));
        ar1 = aRepository.save(ar1);
        //endregion



        //Regeion LIGUE
        Ligue l1 = Ligue.builder()
                .id(0L)
                .nom("Boxe")
                .build();
        Ligue l2 = Ligue.builder()
                .id(0L)
                .nom("Yu-Gi-Yo")
                .build();
        l1 = lRepository.save(l1);
        l2 = lRepository.save(l2);
        //endregion

        //region JOUEUR
        Joueur j1 = new Joueur();
        j1.setId(0L);
        j1.setNom("Terrieur");
        j1.setPrenom("Alex");
        j1.setAge(26);
        j1.setUsername("Alx");
        j1.setPassword(encoder.encode("pass"));
        j1.setRoles(List.of("USER"));
        j1.setLigues(List.of(l1));
        Joueur j2 = new Joueur();
        j2.setId(0L);
        j2.setNom("Terrieur");
        j2.setPrenom("Alain");
        j2.setAge(22);
        j2.setUsername("Alx_2");
        j2.setPassword(encoder.encode("pass"));
        j2.setRoles(List.of("USER"));
        j2.setLigues(List.of(l1));
        j1 = jRepository.save(j1);
        j2 = jRepository.save(j2);
        //endregion

        //region AFFRONTEMENT
        Affrontement aff = Affrontement.builder()
                .id(0L)
                .dateDebut(LocalDateTime.now())
                .dateFin(LocalDateTime.now().plusMinutes(10L))
                .etat(EtatAffrontement.RECHERCHE)
                .ligue(l1)
                .joueur1(j1)
//                .joueur2(j2)
                .vainqueur(j1)
                .arbitre(ar1)
                .build();
        affrontementRepository.save(aff);
        //endregion
    }
}
