package Tema1.Boletin2.Ejercicio15;



public class CalculaMultiplicacion {

	public static void main(String[] args) {
		int num1 = Integer.parseInt(args[0]);

		int num2 = Integer.parseInt(args[1]);

		CalculaMultiplicacion calcula = new CalculaMultiplicacion();

		System.out.println(calcula.calularMultiplicacion(num1, num2));
	}
	
	public int calularMultiplicacion(int num1, int num2) {

		int total = 1;
		
		for(int i = num1; i <= num2; i++) {
			total = total*i;
		}
		//prueba
		return total;

	}
	
}