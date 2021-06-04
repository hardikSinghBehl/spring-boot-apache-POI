package com.hardik.bharta.service;

import java.util.List;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hardik.bharta.dto.SuperHeroCreationRequestDto;
import com.hardik.bharta.entity.SuperHero;
import com.hardik.bharta.exception.DuplicateSuperHeroNameException;
import com.hardik.bharta.repository.SuperHeroRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SuperHeroService {

	private final SuperHeroRepository superHeroRepository;

	public ResponseEntity<List<SuperHero>> retreiveAll() {
		return ResponseEntity.ok(superHeroRepository.findAll());
	}

	public ResponseEntity<?> create(final SuperHeroCreationRequestDto superHeroCreationRequest) {

		if (existsByName(superHeroCreationRequest.getName()))
			throw new DuplicateSuperHeroNameException();

		final var superHero = new SuperHero();
		superHero.setName(superHeroCreationRequest.getName());
		superHero.setAlterEgo(superHeroCreationRequest.getAlterEgoName());
		superHero.setMasterComicId(superHeroCreationRequest.getComicId());
		superHeroRepository.save(superHero);

		return ResponseEntity.ok(new JSONObject().put("message", "Super hero created successfully").toString());
	}

	public boolean existsByName(final String name) {
		return superHeroRepository.existsByName(name);
	}

}
