package com.pngabo.affrontementapi.repositories;

import com.pngabo.affrontementapi.model.entities.Affrontement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AffrontementRepository extends JpaRepository<Affrontement, Long> {
    @Query(value = "SELECT * FROM Affrontement WHERE Ligue_id = ?1", nativeQuery = true)
    List<Affrontement> findByLigueId(Long id);
}
