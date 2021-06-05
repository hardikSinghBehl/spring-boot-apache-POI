package com.hardik.bharta.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hardik.bharta.entity.MasterComic;

@Repository
public interface MasterComicRepository extends JpaRepository<MasterComic, Integer> {

	Optional<MasterComic> findByName(String name);

}
