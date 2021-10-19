package com.pngabo.affrontementapi.repositories;

import com.pngabo.affrontementapi.model.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
