package Tema1.Boletin1;

public class CalculaSuma {
	public static void main(String[] args) {
		String par = args[0];
		int num = Integer.parseInt(args[1]);
		CalculaSuma c = new CalculaSuma();
		c.lanzaSuma(par, num);
	}

	public int lanzaSuma(String par, int num) {
		int suma = 0;

		if (par.equals("par")) {
			for (int i = 0; i <= num; i++) {
				if (i % 2 == 0) {
					suma += i;
				}
			}
			System.out.println("Suma de pares hasta " + num + ": " + suma);
		} else if (par.equals("impar")) {
			for (int i = 0; i <= num; i++) {
				if (i % 2 != 0) {
					suma += i;
				}
			}
			System.out.println("Suma de impares hasta " + num + ": " + suma);
		} else {
			System.out.println("El primer argumento debe ser 'par' o 'impar'.");
		}

		return suma;
	}
}
