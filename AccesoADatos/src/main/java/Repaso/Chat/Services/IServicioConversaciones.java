package Repaso.Chat.Services;

import java.time.LocalDate;

import Repaso.Chat.Exceptions.ConversacionException;
import Repaso.Chat.Models.Conversacion;
import Repaso.Chat.Models.TipoAgente;

public interface IServicioConversaciones {
	public void registraNuevaConveracion(TipoAgente tipo, String pregunta,
			String respuesta);
			public Conversacion getRecuperaConversacion(TipoAgente tipo, String
			pregunta, LocalDate fecha);
			public boolean eliminaConversacion(LocalDate fecha, TipoAgente tipo)
			throws ConversacionException;
			public boolean incrementaNumeroValoraciones(LocalDate fecha,
			TipoAgente tipo, String pregunta);
			public double getValoracionMediaParaHumanos();
			public double getValoracionMedidaParaBots();
			}