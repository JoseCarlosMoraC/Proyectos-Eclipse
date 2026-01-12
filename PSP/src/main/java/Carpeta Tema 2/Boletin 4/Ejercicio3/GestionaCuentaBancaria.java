package Tema2.Boletin4.Ejercicio3;

public class GestionaCuentaBancaria {
	double aleatorio;
    public static void main(String[] args) {
    	GestionaCuentaBancaria g = new GestionaCuentaBancaria();
        HiloIngresa ingresa = new HiloIngresa(g.aleatorio);
        HiloRetira retiro = new HiloRetira(g.aleatorio );
        HiloIngresa ingresa2 = new HiloIngresa(g.aleatorio);
        HiloRetira retiro2 = new HiloRetira(g.aleatorio );
     

        CuentaBancaria cuenta = new CuentaBancaria(100);
  
        
     
        ingresa.setCuenta(cuenta);
        retiro.setCuenta(cuenta);
        ingresa2.setCuenta(cuenta);
        retiro2.setCuenta(cuenta);
      
        try {
            ingresa.start();
            retiro.start();
            ingresa2.start();
            retiro2.start();
        
            ingresa.join();
            retiro.join(); 
            ingresa2.join();
            retiro2.join();
         
            
            System.out.println("Saldo final: " + cuenta.getSaldo() + "€");
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}