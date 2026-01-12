package Tema2.Boletin3.Ejercicio1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GestionaCalculaMaxHilo {
    public static void main(String[] args) {
        int tamTabla= 10000000;
        int numHilos = 10;
        long t_comienzo = System.currentTimeMillis();

        GestionaCalculaMaxHilo calcula = new GestionaCalculaMaxHilo();

        int[] tabla = calcula.construyeTablaNotas(tamTabla);
        List<Thread> hilos  = new ArrayList<>();
        List<CalculaMaxHilo> objetosHilos  = new ArrayList<>();

        try {
            int rango = tamTabla / numHilos;
            for(int i = 0; i< numHilos; i++) {
                CalculaMaxHilo c = new CalculaMaxHilo();
                c.setTabla(tabla);
                c.setInicio(i*rango);
                // Ajuste del último hilo
                if (i == numHilos - 1) {
                    c.setFin(tamTabla - 1);
                } else {
                    c.setFin(c.getInicio() + rango - 1);
                }
                objetosHilos.add(c);
                Thread hilo = new Thread(c);
                hilos.add(hilo);
            }

            for(Thread h : hilos) h.start();
            for(Thread h : hilos) h.join();

            // Calcular máximo total
            int maxTotal = Integer.MIN_VALUE;
            for (CalculaMaxHilo c : objetosHilos) {
                if (c.getMaximoTramo() > maxTotal) {
                    maxTotal = c.getMaximoTramo();
                }
            }
            System.out.println("Máximo total = " + maxTotal);

            long t_fin = System.currentTimeMillis();
            long tiempototal = t_fin - t_comienzo;
            System.out.println("El proceso total ha tardado= "+tiempototal+" mseg");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    int[] construyeTablaNotas(int tamanyo) {
        int[] tabla = new int[tamanyo];
        Random r = new Random();
        for (int i = 0; i < tabla.length; i++) {
            tabla[i] = r.nextInt(5000) + 1;
        }
        return tabla;
    }
}
