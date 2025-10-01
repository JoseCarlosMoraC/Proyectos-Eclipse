package Repaso.Chat.Repositories;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import Repaso.Chat.Exceptions.ConversacionException;
import Repaso.Chat.Models.Conversacion;
import Repaso.Chat.Models.TipoAgente;

public class RepositorioConversaciones implements IRepositorioConversaciones{
Set<Conversacion> conversaciones;


	public RepositorioConversaciones(Set<Conversacion> conversaciones) {
	super();
	this.conversaciones = new HashSet<Conversacion>();
}


	public Set<Conversacion> getConversaciones() {
		return conversaciones;
	}


	public void setConversaciones(Set<Conversacion> conversaciones) {
		this.conversaciones = conversaciones;
	}


	
	public Conversacion getConversacion(LocalDate fecha, TipoAgente tipo, String pregunta) {
		boolean encontrado = false;
	    Conversacion conversacion = null;
	    Iterator<Conversacion> it = conversaciones.iterator();
	    while (it.hasNext() && !encontrado) {
	        Conversacion c = it.next();
	        if (c.getFechaConversacion().equals(fecha)&&c.getTipo().equals(tipo)&&c.getPregunta().equalsIgnoreCase(pregunta)) {
	            conversacion = c;
	            encontrado = true;
	        }
	    }
	    return conversacion;
	}
	


	@Override
	public void agregaConversacion(TipoAgente tipo, String pregunta, String respuesta) {
		// TODO Auto-generated method stub
		 boolean encontrado = false;
		   Conversacion conversacion = null;

		  
		    Iterator<Conversacion> it = conversaciones.iterator();
		    while (it.hasNext() && !encontrado) {
		        Conversacion c = it.next();

		        
		        if (c.getPregunta().equalsIgnoreCase(pregunta)&& c.getTipo().equals(tipo)&& c.getRespuesta().equalsIgnoreCase(respuesta)){
		            encontrado = true;
		     
		          
		        
		    }else {
		    	Conversacion nuevaConversacion = new Conversacion();
		    	nuevaConversacion.setPregunta(pregunta);
		    	nuevaConversacion.setTipo(tipo);
		    	nuevaConversacion.setRespuesta(respuesta);
		    	conversaciones.add(nuevaConversacion);

		    }
		     
		    }
		
		}
	


	@Override
	public boolean contieneConversacionConversacion(Conversacion conversacion) {
		// TODO Auto-generated method stub
		
		

		   
		    return conversaciones.contains(conversacion);
		}

	


	@Override
	public void eliminaConversacion(LocalDate fecha, TipoAgente tipo, String pregunta) throws ConversacionException {
		// TODO Auto-generated method stub
		 boolean encontrado = false;
		   Conversacion conversacion = null;

		  
		    Iterator<Conversacion> it = conversaciones.iterator();
		    while (it.hasNext() && !encontrado) {
		        Conversacion c = it.next();

		        
		        if (c.getPregunta().equalsIgnoreCase(pregunta)&& c.getTipo().equals(tipo)&& c.getFechaConversacion().equals(fecha)){
		            encontrado = true;
		            throw new ConversacionException("No existe");
		          
		        
		    }else {
		    	conversaciones.remove(fecha);
		    	conversaciones.remove(tipo);
		    	

		    }
		     
		    }
		
		}
	


	@Override
	public void incrementaNumeroValoraciones(LocalDate fecha, TipoAgente tipo, String pregunta, double valoracion)
			throws ConversacionException {
		// TODO Auto-generated method stub
		Conversacion c = getConversacion(fecha, tipo, pregunta);
		if (c==null) {
			throw new ConversacionException("No existe");
		}else {
			c.setNumValoracionesPositivas(+1);
		}
		
	}
}


