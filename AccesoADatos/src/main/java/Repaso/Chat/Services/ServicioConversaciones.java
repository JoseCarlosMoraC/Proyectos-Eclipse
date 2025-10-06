package Repaso.Chat.Services;

import java.time.LocalDate;

import Repaso.Chat.Exceptions.ConversacionException;
import Repaso.Chat.Models.Conversacion;
import Repaso.Chat.Models.TipoAgente;

public class ServicioConversaciones implements IServicioConversaciones{

	@Override
	public void registraNuevaConveracion(TipoAgente tipo, String pregunta, String respuesta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Conversacion getRecuperaConversacion(TipoAgente tipo, String pregunta, LocalDate fecha) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean eliminaConversacion(LocalDate fecha, TipoAgente tipo) throws ConversacionException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean incrementaNumeroValoraciones(LocalDate fecha, TipoAgente tipo, String pregunta) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double getValoracionMediaParaHumanos() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getValoracionMedidaParaBots() {
		// TODO Auto-generated method stub
		return 0;
	}

}
