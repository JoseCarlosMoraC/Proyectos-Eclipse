package com.spring.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.exceptions.CoachXNotFoundException;
import com.spring.models.Equipo;
import com.spring.repositories.EquipoRepository;

@Service
public class EquipoServiceImpl implements EquipoService {

	private EquipoRepository equipoRepository;

	@Autowired
	public EquipoServiceImpl(EquipoRepository equipoRepository) {
		super();
		this.equipoRepository = equipoRepository;
	}

	@Override
	public List<Equipo> findAll() {
		return equipoRepository.findAll();
	}

	@Override
	public Set<Equipo> findByCategoria(String categoria) {
		return equipoRepository.findByCategoria(categoria);
	}

	@Override
	public String createEquipo(Equipo equipo) {
		String respuesta = "";
		Equipo equipoAñadido = equipoRepository.save(equipo);
		if (equipoAñadido != null) {
			respuesta = equipoAñadido.getNombre().toString();
		} else {
			respuesta = "No se ha podido añadir el equipo";
		}
		return respuesta;
	}

	@Override
	public Equipo findEquipoById(long id) {
		return equipoRepository.findById(id)
				.orElseThrow(() -> new CoachXNotFoundException(id));
	}

	@Override
	public Equipo updateNameEquipo(long id, Equipo equipo) {
		Equipo eOriginal = this.findEquipoById(id);
		eOriginal.setNombre(equipo.getNombre());
		return equipoRepository.save(eOriginal);
	}
}