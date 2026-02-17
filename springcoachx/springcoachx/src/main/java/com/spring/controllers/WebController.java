package com.spring.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.spring.models.Equipo;
import com.spring.models.Jugador;
import com.spring.models.Partido;
import com.spring.models.Resultado;
import com.spring.services.EquipoService;
import com.spring.services.JugadorService;
import com.spring.services.PartidoService;

import exceptions.CoachXNotFoundException;

@Controller
@RequestMapping("/coachx")
public class WebController {

	@Autowired
	private EquipoService equipoService;

	@Autowired
	private JugadorService jugadorService;

	@Autowired
	private PartidoService partidoService;


	@GetMapping("/")
	public String index() {
		return "index";
	}

	
	@GetMapping("/lista")
	public String catalog(Model model) {
		List<Equipo> equipos = equipoService.findAll();
		model.addAttribute("equipos", equipos);
		return "equipos";
	}

	@GetMapping("/equipo/{id}")
	public String getEquipoById(@PathVariable Long id, Model model) {
		Equipo equipo = equipoService.findEquipoById(id);
		model.addAttribute("detalleEquipos", equipo);
		return "detalle";
	}


	@GetMapping("/equipos/categoria")
	@ResponseBody
	public ResponseEntity<?> getEquiposByCategoria(@RequestParam String categoria) {
		Set<Equipo> equipos = equipoService.findByCategoria(categoria);
		if (equipos.isEmpty()) {
			return new ResponseEntity<>(
				Response.errorResonse(Response.NOT_FOUND, "No se encontraron equipos con la categoría: " + categoria),
				HttpStatus.NOT_FOUND
			);
		}
		return new ResponseEntity<>(equipos, HttpStatus.OK);
	}


	@PostMapping("/equipo")
	@ResponseBody
	public String addEquipo(@RequestBody Equipo equipo) {
		return equipoService.createEquipo(equipo);
	}


	@PutMapping("/equipo/{id}")
	@ResponseBody
	public ResponseEntity<?> updateEquipo(@PathVariable Long id, @RequestBody Equipo equipo) {
		Equipo equipoActualizado = equipoService.updateNameEquipo(id, equipo);
		return new ResponseEntity<>(equipoActualizado, HttpStatus.OK);
	}

	
	@GetMapping("/jugadores")
	public String listJugadores(Model model) {
		List<Jugador> jugadores = jugadorService.findAll();
		model.addAttribute("jugadores", jugadores);
		return "jugadores";
	}


	@GetMapping("/jugador/{id}")
	public String getJugadorById(@PathVariable Long id, Model model) {
		Jugador jugador = jugadorService.findJugadorById(id);
		model.addAttribute("detalleJugador", jugador);
		return "detalleJugador";
	}


	@GetMapping("/jugadores/posicion")
	@ResponseBody
	public ResponseEntity<?> getJugadoresByPosicion(@RequestParam String posicion) {
		Set<Jugador> jugadores = jugadorService.findByPosicion(posicion);
		if (jugadores.isEmpty()) {
			return new ResponseEntity<>(
				Response.errorResonse(Response.NOT_FOUND, "No se encontraron jugadores con la posición: " + posicion),
				HttpStatus.NOT_FOUND
			);
		}
		return new ResponseEntity<>(jugadores, HttpStatus.OK);
	}

	
	@PostMapping("/jugador")
	@ResponseBody
	public String addJugador(@RequestBody Jugador jugador) {
		return jugadorService.createJugador(jugador);
	}


	@PutMapping("/jugador/{id}")
	@ResponseBody
	public ResponseEntity<?> updateJugador(@PathVariable Long id, @RequestBody Jugador jugador) {
		Jugador jugadorActualizado = jugadorService.updateNameJugador(id, jugador);
		return new ResponseEntity<>(jugadorActualizado, HttpStatus.OK);
	}


	@GetMapping("/partidos")
	public String listPartidos(Model model) {
		List<Partido> partidos = partidoService.findAll();
		model.addAttribute("partidos", partidos);
		return "partidos";
	}

	
	@GetMapping("/partido/{id}")
	public String getPartidoById(@PathVariable Long id, Model model) {
		Partido partido = partidoService.findPartidoById(id);
		model.addAttribute("detallePartido", partido);
		return "detallePartido";
	}


	@GetMapping("/partidos/resultado")
	@ResponseBody
	public ResponseEntity<?> getPartidosByResultado(@RequestParam Resultado resultado) {
		Set<Partido> partidos = partidoService.findByResultado(resultado);
		if (partidos.isEmpty()) {
			return new ResponseEntity<>(
				Response.errorResonse(Response.NOT_FOUND, "No se encontraron partidos con el resultado: " + resultado),
				HttpStatus.NOT_FOUND
			);
		}
		return new ResponseEntity<>(partidos, HttpStatus.OK);
	}


	@PostMapping("/partido")
	@ResponseBody
	public String addPartido(@RequestBody Partido partido) {
		return partidoService.createPartido(partido);
	}


	@PutMapping("/partido/{id}")
	@ResponseBody
	public ResponseEntity<?> updatePartido(@PathVariable Long id, @RequestBody Partido partido) {
		Partido partidoActualizado = partidoService.updateNamePartido(id, partido);
		return new ResponseEntity<>(partidoActualizado, HttpStatus.OK);
	}

	// ── MANEJO DE EXCEPCIONES ────────────────────────────────────────────────────

	@ExceptionHandler(CoachXNotFoundException.class)
	public String handleNotFoundException(CoachXNotFoundException ex, Model model) {
		model.addAttribute("mensaje", "No hemos encontrado lo que buscas. Por favor, vuelve al inicio.");
		return "error";
	}
}