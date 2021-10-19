package com.pngabo.affrontementapi.repositories;

import com.pngabo.affrontementapi.model.entities.Affrontement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AffrontementRepository extends JpaRepository<Affrontement, Long> {
}
