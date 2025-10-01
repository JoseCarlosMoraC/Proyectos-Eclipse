package Repaso.Chat.Services;

public interface IServicioConversaciones {
	public void registraNuevaConveracion(TipoAgente tipo, String pregunta,
			String respuesta) {
			}
			public Conversacion getRecuperaConversacion(TipoAgente tipo, String
			pregunta, LocalDate fecha) {
				return null;
			}
			public boolean eliminaConversacion(LocalDate fecha, TipoAgente tipo)
			throws ConversacionException {
				return false;
			}
			public boolean incrementaNumeroValoraciones(LocalDate fecha,
			TipoAgente tipo, String pregunta) {
				return false;
			}
			public double getValoracionMediaParaHumanos() {
				return 0;
			}
			public double getValoracionMedidaParaBots() {
				return 0;
			}
			}