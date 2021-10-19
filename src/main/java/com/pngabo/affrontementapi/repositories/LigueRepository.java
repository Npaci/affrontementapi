package com.pngabo.affrontementapi.repositories;

import com.pngabo.affrontementapi.model.entities.Ligue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LigueRepository extends JpaRepository<Ligue, Long> {
}
