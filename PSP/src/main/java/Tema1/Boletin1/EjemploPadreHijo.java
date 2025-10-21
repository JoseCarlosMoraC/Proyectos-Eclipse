package Tema1.Boletin1;

import java.io.File;

public class EjemploPadreHijo {
    private final static String directorioGenerarClasses = "C:\\Users\\alumno\\Desktop\\DAM\\Proyectos Eclipse\\PSP\\target";
    private final static String rutaSourceJava = "C:\\Users\\alumno\\Desktop\\DAM\\Proyectos Eclipse\\PSP\\src\\main\\java\\Tema1\\Boletin1\\EjemploPadreHijo.java";

    public static void main(String[] args) {
        EjemploPadreHijo l = new EjemploPadreHijo();
        l.lanzaCompilador(rutaSourceJava, directorioGenerarClasses);

        System.out.println(args[0]);
        System.out.println(args[1]);
    }

    public void lanzaCompilador(String rutaFicheroFuente, String rutaParaGenerarClass) {
        ProcessBuilder pb;
        try {
            File fuente = new File(rutaFicheroFuente);
            File destino = new File(rutaParaGenerarClass);

            String[] comando = {
                "javac",
                "-d",
                destino.getAbsolutePath(),
                fuente.getAbsolutePath()
            };

            pb = new ProcessBuilder(comando);
            pb.redirectErrorStream(true);
            pb.inheritIO();

            Process proceso = pb.start();
            int exit = proceso.waitFor();
            System.out.println("Salida: " + exit);

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}

