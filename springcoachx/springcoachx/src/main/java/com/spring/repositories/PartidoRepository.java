package com.spring.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.models.Partido;
import com.spring.models.Resultado;



@Repository
public interface PartidoRepository extends JpaRepository<Partido, Long> {
	List<Partido> findAll();

	Set<Partido> findByResultado(Resultado resultado);;

	Partido findPartidoById(long id);
}
