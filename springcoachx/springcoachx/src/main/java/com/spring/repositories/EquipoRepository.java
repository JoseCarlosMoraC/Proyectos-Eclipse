package com.spring.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.models.Equipo;


@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Long> {
	List<Equipo> findAll();

	Set<Equipo> findByCategoria(String categoria);

	Equipo findEquipoById(long id);
}

