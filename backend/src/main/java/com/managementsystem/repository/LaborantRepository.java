package com.managementsystem.repository;

import com.managementsystem.entity.Laborant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaborantRepository extends JpaRepository<Laborant, Long> {
}
