package com.travelling_project.repository;

import com.travelling_project.entity.Traveller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravellerRepository extends JpaRepository<Traveller, Long> {
    }

