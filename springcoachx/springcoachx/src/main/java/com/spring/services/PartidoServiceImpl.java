package com.spring.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.models.Partido;
import com.spring.models.Resultado;
import com.spring.repositories.PartidoRepository;

import exceptions.CoachXNotFoundException;

@Service
public class PartidoServiceImpl implements PartidoService {

	private PartidoRepository partidoRepository;

	@Autowired
	public PartidoServiceImpl(PartidoRepository partidoRepository) {
		super();
		this.partidoRepository = partidoRepository;
	}

	@Override
	public List<Partido> findAll() {
		return partidoRepository.findAll();
	}

	@Override
	public Set<Partido> findByResultado(Resultado resultado) {
		return partidoRepository.findByResultado(resultado);
	}

	@Override
	public String createPartido(Partido partido) {
		String respuesta = "";
		Partido partidoAñadido = partidoRepository.save(partido);
		if (partidoAñadido != null) {
			respuesta = partidoAñadido.getLugar().toString();
		} else {
			respuesta = "No se ha podido añadir el partido";
		}
		return respuesta;
	}

	@Override
	public Partido findPartidoById(long id) {
		return partidoRepository.findById(id)
				.orElseThrow(() -> new CoachXNotFoundException(id));
	}

	@Override
	public Partido updateNamePartido(long id, Partido partido) {
		Partido pOriginal = this.findPartidoById(id);
		pOriginal.setLugar(partido.getLugar());
		return partidoRepository.save(pOriginal);
	}
}