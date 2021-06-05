package com.hardik.bharta.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.Data;
import lombok.EqualsAndHashCode.Exclude;

@Data
@Entity
@Table(name = "master_comics")
public class MasterComic implements Serializable {

	private static final long serialVersionUID = -3197702603107771751L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, unique = true, updatable = false)
	private Integer id;

	@Column(name = "name", nullable = false, unique = true, length = 50)
	private String name;

	@Hidden
	@Exclude
	@JsonIgnore
	@OneToMany(mappedBy = "masterComic", fetch = FetchType.LAZY)
	private List<SuperHero> superHeros;

}
