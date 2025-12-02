package Tema2.Boletin4.Ejercicio3;

public class CuentaBancaria {
    private double saldo;


  
    public CuentaBancaria(double saldo) {
        this.saldo = saldo;
   
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "CuentaBancaria [saldo=" + saldo + "]";
    }

    public synchronized void ingresarDinero(double importe) {
        saldo = this.saldo + importe;
        System.out.println("Ingresado: " + importe + "€");
        System.out.println("Saldo total: " + saldo + "€");
    }
    
    public synchronized void retirarDinero(double retiro) throws CuentaException {
        if (retiro > saldo) {
            throw new CuentaException("No hay dinero para retirar, estás pobre bb");
        }
        saldo = this.saldo - retiro;
        System.out.println("Retirado: " + retiro + "€");
        System.out.println("Saldo total: " + saldo + "€");
    }
}