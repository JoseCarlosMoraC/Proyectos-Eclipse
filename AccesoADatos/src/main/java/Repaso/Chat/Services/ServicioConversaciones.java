package Repaso.Chat.Services;

import java.time.LocalDate;

import Repaso.Chat.Exceptions.ConversacionException;
import Repaso.Chat.Models.Conversacion;
import Repaso.Chat.Models.TipoAgente;
import Repaso.Chat.Repositories.RepositorioConversaciones;

public class ServicioConversaciones implements IServicioConversaciones{
private RepositorioConversaciones repo;

	public ServicioConversaciones(RepositorioConversaciones repo) {
	super();
	this.repo = repo;
}

	@Override
	public void registraNuevaConveracion(TipoAgente tipo, String pregunta, String respuesta) {
		// TODO Auto-generated method stub
		repo.agregaConversacion(tipo, pregunta, respuesta);
	}

	@Override

	public Conversacion getRecuperaConversacion(TipoAgente tipo, String pregunta, LocalDate fecha) {
	    return repo.getConversacion(fecha, tipo, pregunta);
	}

	
	


	@Override
	public boolean incrementaNumeroValoraciones(LocalDate fecha, TipoAgente tipo, String pregunta) {
	   
	   
				try {
					repo.incrementaNumeroValoraciones(fecha, tipo, pregunta, 1);
				} catch (ConversacionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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


	public boolean eliminaConversacion(LocalDate fecha, TipoAgente tipo) throws ConversacionException {
		// TODO Auto-generated method stub
	   
	        repo.eliminaConversacion(fecha, tipo, null);
			return false;
			
	     
	
	}
	}


