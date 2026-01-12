package Tema1.Boletin1;

import java.io.IOException;

public class Lanzador {
	private static final String directorioGenerarClases = "C:\\Users\\alumno\\Desktop\\DAM\\Proyectos Eclipse\\PSP\\target" ;
	private static final String rutaFicherosJava = "C:\\Users\\alumno\\Desktop\\DAM\\Proyectos Eclipse\\PSP\\src\\main\\java";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Lanzador lanzador = new Lanzador();
		lanzador.ejecutaProcesoCompila();
	}
		public void ejecutaProcesoCompila() {
			String [] comando = {"javac", "-d", directorioGenerarClases, rutaFicherosJava + "\\Tema1\\Boletin1\\Gestiona.java"};
			ProcessBuilder pb = new ProcessBuilder(comando);
			
			try {
				Process proceso1 = pb.start();
				
			}catch(IOException e) {
				e.printStackTrace();
			}
			
			
			}
		public void ejecutaProcesoJava() {
			String[] comando = {"java", "-cp", "target/classes", "\\Tema1\\Boletin1\\Gestiona.java" };
		}
		}
		 
	

