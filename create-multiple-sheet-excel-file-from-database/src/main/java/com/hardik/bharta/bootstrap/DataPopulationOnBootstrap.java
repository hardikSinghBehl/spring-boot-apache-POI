package com.hardik.bharta.bootstrap;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.hardik.bharta.entity.MasterComic;
import com.hardik.bharta.entity.SuperHero;
import com.hardik.bharta.repository.MasterComicRepository;
import com.hardik.bharta.repository.SuperHeroRepository;

import lombok.AllArgsConstructor;
import net.bytebuddy.utility.RandomString;

@Component
@AllArgsConstructor
public class DataPopulationOnBootstrap {

	private final MasterComicRepository masterComicRepository;
	private final SuperHeroRepository superHeroRepository;

	@PostConstruct
	void populatingMasterComicData() {
		final var comicNameList = List.of("Detective Comics", "Marvel Comics", "Other");

		comicNameList.forEach(comicName -> {
			final var comic = new MasterComic();
			comic.setName(comicName);
			masterComicRepository.save(comic);
		});
	}

	@PostConstruct
	void populatingRealSuperHeroData() {
		final var superHeroDataList = List.of(List.of("BATMAN", "Bruce Wayne", 1),
				List.of("WONDER WOMAN", "Diana Prince", 1), List.of("SUPERMAN", "Clark Kent", 1),
				List.of("FLASH", "Barry Allen", 1), List.of("AQUAMAN", "Arthur Curry", 1),
				List.of("GREEN ARROW", "Oliver Queen", 1), List.of("MARTIAN MANHUNTER", "John Jones", 1),
				List.of("SPIDER MAN", "Peter Parker", 2), List.of("IRON MAN", "Tony Stark", 2),
				List.of("HULK", "Bruce Banner", 2), List.of("THOR", "Donald Blake", 2),
				List.of("CAPTAIN AMERICA", "Steve Rogers", 2), List.of("BLACK WIDOW", "Natasha Romanoff", 2));

		superHeroDataList.forEach(superHeroData -> {
			final var superHero = new SuperHero();
			superHero.setName(((String) superHeroData.get(0)));
			superHero.setAlterEgo(((String) superHeroData.get(1)));
			superHero.setMasterComicId(((Integer) superHeroData.get(2)));
			superHeroRepository.save(superHero);
		});
	}

	@PostConstruct
	void populatingRandomSuperHeroData() {
		for (int count = 0; count <= 30; count++) {
			final var superHero = new SuperHero();
			superHero.setAlterEgo(RandomString.make(5));
			superHero.setName(RandomString.make(4) + RandomString.make(7));
			superHero.setMasterComicId(3);
			superHeroRepository.save(superHero);
		}
	}
}
