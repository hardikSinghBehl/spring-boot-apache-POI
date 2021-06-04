package com.hardik.bharta.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hardik.bharta.entity.SuperHero;

@Repository
public interface SuperHeroRepository extends JpaRepository<SuperHero, UUID> {

	boolean existsByName(String name);

}
