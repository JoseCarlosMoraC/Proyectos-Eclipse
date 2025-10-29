package TowerGPT.Repository;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import TowerGPT.Models.InteraccionAgente;
import TowerGPT.Models.TipoAgente;
import TowerGPT.Service.IServicioEstadistica;

public class RepositorioInteracciones {
	private IServicioEstadistica servicio;
	private Map<String, InteraccionAgente> registroInteracciones; 

	public RepositorioInteracciones(IServicioEstadistica servicio) {
		super();
		this.servicio = servicio;
		this.registroInteracciones = new HashMap<String, InteraccionAgente>();
	}

	public IServicioEstadistica getServicio() {
		return servicio;
	}

	public void setServicio(IServicioEstadistica servicio) {
		this.servicio = servicio;
	}

	public Map<String, InteraccionAgente> getRegistroInteracciones() {
		return registroInteracciones;
	}

	@Override
	public String toString() {
		return "RepositorioInteracciones [servicio=" + servicio + ", registroSize=" + registroInteracciones.size()
				+ "]";
	}

	public String obtenerInteraccionConMejorValoracion() {
		InteraccionAgente ia = servicio.obtenerInteraaccionConMejorValoracion(registroInteracciones);
		if (ia == null)
			return "No hay interacciones.";
		return ia.toString();
	}

	public String mostrarInteraccionesAgrupadasPorTipo() {
		Map<TipoAgente, List<InteraccionAgente>> mapa = servicio.agruparInteraccionesPorTipo(registroInteracciones);
		StringBuilder sb = new StringBuilder();
		if (mapa != null) {
			List<InteraccionAgente> humanos = mapa.get(TipoAgente.HUMANO);
			List<InteraccionAgente> ias = mapa.get(TipoAgente.IA);

			sb.append("--- HUMANOS ---\n");
			if (humanos != null) {
				for (int i = 0; i < humanos.size(); i++) {
					sb.append(humanos.get(i).toString()).append("\n");
				}
			}
			sb.append("\n--- IA ---\n");
			if (ias != null) {
				for (int i = 0; i < ias.size(); i++) {
					sb.append(ias.get(i).toString()).append("\n");
				}
			}
		}
		return sb.toString();
	}

	public void grabarResumenEstadistica(String rutaFicheroNombre) {
		servicio.grabarResumenEstadistica(rutaFicheroNombre, registroInteracciones);
	}

	public String obtenerInteraccionesAciertoMayorOrdenadas(double porcentajeAcierto) {
		List<InteraccionAgente> lista = new ArrayList<InteraccionAgente>();
		for (String k : registroInteracciones.keySet()) {
			InteraccionAgente ia = registroInteracciones.get(k);
			if (ia != null && ia.getPorcentajeAcierto() > porcentajeAcierto) {
				lista.add(ia);
			}
		}

		for (int i = 0; i < lista.size() - 1; i++) {
			for (int j = i + 1; j < lista.size(); j++) {
				InteraccionAgente a = lista.get(i);
				InteraccionAgente b = lista.get(j);
				boolean swap = false;
				if (a.getPorcentajeAcierto() < b.getPorcentajeAcierto()) {
					swap = true;
				} else if (a.getPorcentajeAcierto() == b.getPorcentajeAcierto()) {
					if (a.getTipo() == TipoAgente.IA && b.getTipo() == TipoAgente.HUMANO) {
						swap = true;
					}
				}
				if (swap) {
					lista.set(i, b);
					lista.set(j, a);
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < lista.size(); i++) {
			sb.append(lista.get(i).toString()).append("\n");
		}
		if (sb.length() == 0)
			sb.append("No hay interacciones con porcentaje mayor a ").append(porcentajeAcierto);
		return sb.toString();
	}

	public void grabarFicherosCSV() {
		String ruta = "src/main/java/TowerGPT/JSON/salidaOrdenada.csv";
		servicio.grabarFicheroCSV(ruta, registroInteracciones);

	}

	public void actualizaPorcentajeInteraccion(InteraccionAgente interaccion, double porcentajeNuevo) {
		boolean actualizado = false;

		if (interaccion != null) {
			String id = interaccion.getIdentificador();

			if (id != null) {
				InteraccionAgente existente = registroInteracciones.get(id);

				if (existente != null) {
					existente.setPorcentajeAcierto(porcentajeNuevo);
					registroInteracciones.put(id, existente);
					actualizado = true;
				} else {
					for (String k : registroInteracciones.keySet()) {
						InteraccionAgente ia = registroInteracciones.get(k);
						if (!actualizado && ia != null && ia.equals(interaccion)) {
							ia.setPorcentajeAcierto(porcentajeNuevo);
							actualizado = true;
						}
					}
				}
			}
		}
	}

	public double obtenerTiempoMedioPorAgente(TipoAgente tipo) {
		return servicio.calcularTiempoMedioPorTipo(tipo, registroInteracciones);
	}

	public double obtenerPorcentajeAciertoMedioPorAgente(TipoAgente tipo) {
		return servicio.calcularPorcentajeAciertoMedioPorTipo(tipo, registroInteracciones);
	}

	public void incrementaNumeroValoraciones(String identificador) {
		boolean actualizado = false;

		if (identificador != null) {
			InteraccionAgente ia = registroInteracciones.get(identificador);

			if (ia != null) {
				ia.setNumValoracionesPositivas(ia.getNumValoracionesPositivas() + 1);
				registroInteracciones.put(identificador, ia);
				actualizado = true;
			} else {
				for (String k : registroInteracciones.keySet()) {
					InteraccionAgente aux = registroInteracciones.get(k);
					if (!actualizado && aux != null && identificador.equals(aux.getIdentificador())) {
						aux.setNumValoracionesPositivas(aux.getNumValoracionesPositivas() + 1);
						actualizado = true;
					}
				}
			}
		}
	}

	public void agregaInteraccionARegistro(InteraccionAgente interaccion) {
		if (interaccion != null) {
			String id = interaccion.getIdentificador();
			if (id == null) {
				id = String.valueOf(registroInteracciones.size() + 1);
				interaccion.setIdentificador(id);
			}
			registroInteracciones.put(id, interaccion);
		}
	}

	public void cargarRegistrosDesdeJSON(String ruta) {
		try {
			Gson gson = new Gson();
			FileReader fr = new FileReader(ruta);
			List<InteraccionAgente> lista = gson.fromJson(fr, new TypeToken<List<InteraccionAgente>>() {
			}.getType());
			if (lista != null) {
				for (int i = 0; i < lista.size(); i++) {
					InteraccionAgente ia = lista.get(i);
					if (ia.getIdentificador() == null) {
						ia.setIdentificador(String.valueOf(i + 1));
					}
					registroInteracciones.put(ia.getIdentificador(), ia);
				}
			}
			fr.close();
			System.out.println("Registros cargados desde JSON correctamente.");
		} catch (Exception e) {
			System.out.println("Error leyendo JSON: " + e.getMessage());
		}
	}
}
