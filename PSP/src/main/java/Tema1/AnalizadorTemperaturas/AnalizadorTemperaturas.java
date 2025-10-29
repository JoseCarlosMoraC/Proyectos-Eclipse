package Tema1.AnalizadorTemperaturas;


	import java.io.FileNotFoundException;
	import java.io.FileReader;
	import java.io.FileWriter;
	import java.io.IOException;
	import java.io.PrintWriter;
	import java.util.Scanner;

	//hijo
	public class AnalizadorTemperaturas {
		
		private static final String fichero = "src/main/resources/";

		public static void main(String[] args) throws FileNotFoundException {
			
			String ruta = fichero + args[0];
			int temp = Integer.parseInt(args[1]);
			
			AnalizadorTemperaturas contador = new AnalizadorTemperaturas();
			int resul = contador.contarTemperatura(ruta, temp);

			String ficheroResultado = fichero + temp + ".txt";
			contador.escribeFichero(ficheroResultado, resul);
			
		}
		
		public int contarTemperatura(String ruta, int temp) throws FileNotFoundException {
			int contador = 0;
			Scanner in = null;
			try {
			
				FileReader fichero = new FileReader(ruta);
			
				in = new Scanner(fichero);
			
				while (in.hasNextLine()) { 
					
					String linea = in.nextLine();
					int c = Integer.parseInt(linea);
					if(c >= temp) {
						contador +=1;	
					}
					
				}
			} finally {
				if (in != null) {
					in.close();
				}
			}
			return contador;
		}
		
		public void escribeFichero(String ruta, int temperatura) {
			
			PrintWriter wr = null;
			try {
				FileWriter ficheroResultado = new FileWriter(ruta);
				wr = new PrintWriter(ficheroResultado);
				
				wr.printf("Numero de veces: %d", temperatura);
				
				
			} catch (IOException e) {
					System.out.println("Error, no se ha creado fichero");	
			}
			finally {
				if(wr!=null) {
					wr.close();
				}
			}
			
		}

	}