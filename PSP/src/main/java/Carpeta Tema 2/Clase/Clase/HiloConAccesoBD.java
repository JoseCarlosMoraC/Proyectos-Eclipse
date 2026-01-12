package Tema2.Clase;

public class HiloConAccesoBD extends Thread {
	   private final ConexionBBDD semaforo;

	   public HiloConAccesoBD(ConexionBBDD semaforo, String nombre) {
	       super(nombre);
	       this.semaforo = semaforo;
	   }

	   @Override
	   public void run() {
	   	semaforo.conexion();
	   }
	}
