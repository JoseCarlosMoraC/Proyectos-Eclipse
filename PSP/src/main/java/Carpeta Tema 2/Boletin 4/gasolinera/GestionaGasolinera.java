package Tema2.Boletin4.gasolinera.gasolinera;

import java.util.ArrayList;
import java.util.List;

public class GestionaGasolinera {
	public static void main(String[] args) {

		Surtidor surtidor = new Surtidor(3);
		int numHilos = 8;
		
		List<Thread> hilos = new ArrayList<>();
		
		for(int i = 0; i < numHilos; i++)
		{
			hilos.add(new Thread(new Coche(surtidor, "Coche "+(i+1))));
		}		
		
		for(Thread hilo : hilos)
		{
			hilo.start();
		}		
	}}

