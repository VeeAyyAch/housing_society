package com.housing.society.repository;

import com.housing.society.entity.Premise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PremiseRepository extends JpaRepository<Premise, Long> {
    Optional<Premise> findByPremiseId(String premiseId);
}
