package Repaso.Controllers;

import java.util.HashSet;
import java.util.Set;

import Repaso.Chat.Models.Conversacion;
import Repaso.Chat.Models.TipoAgente;
import Repaso.Chat.Repositories.RepositorioConversaciones;
import Repaso.Chat.Services.ServicioConversaciones;

public class GestionaPeticionesAChat {
	 public static void main(String[] args) {
		 	Set<Conversacion> baseDatos = new HashSet<>();
	        RepositorioConversaciones repo = new RepositorioConversaciones(baseDatos);
	        ServicioConversaciones servicio = new ServicioConversaciones(repo);

	 
	        servicio.registraNuevaConveracion(TipoAgente.Humano, "¿Cuál es tu horario?", "De 9 a 18h");
	        servicio.registraNuevaConveracion(TipoAgente.IA, "¿Qué es Java?", "Un lenguaje de programación.");
	        servicio.registraNuevaConveracion(TipoAgente.IA, "¿Quién eres?", "Soy un asistente virtual.");
	        System.out.println("Conversaciones registradas");
}
}
