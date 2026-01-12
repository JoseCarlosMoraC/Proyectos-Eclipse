package Tema2.Boletin1.Ejercicio5;


import java.util.ArrayList;
import java.util.List;

public class GestionaMascota {

    public static void main(String[] args) {
        Mascota lulu = new Mascota("lulú", 0);
        List<Thread> cuidadores = new ArrayList<>();
        Thread Cuidador1 = new Thread();
        Thread Cuidador2 = new Thread();
        Thread Cuidador3 = new Thread();
        Thread Cuidador4 = new Thread();
        Thread Cuidador5 = new Thread();
        

  
        for (int i = 10; i > 0; i--) {
            Thread nombrecuidador = new Thread(lulu, "cuidador" + i);
            nombrecuidador.setPriority(i);
            
           /* if (i % 2 == 0) {
                nombrecuidador.setPriority(Thread.MAX_PRIORITY);
            }*/
            

            cuidadores.add(nombrecuidador);
            nombrecuidador.start();
        }

  
        for (Thread thread : cuidadores) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
