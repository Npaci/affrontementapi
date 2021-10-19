package com.pngabo.affrontementapi.repositories;

import com.pngabo.affrontementapi.model.entities.Joueur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JoueurRepository extends JpaRepository<Joueur, Long> {
}
