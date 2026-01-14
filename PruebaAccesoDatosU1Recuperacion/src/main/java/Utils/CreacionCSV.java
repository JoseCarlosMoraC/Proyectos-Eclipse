package Utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import Models.Concierto;
import Models.Grupo;

public class CreacionCSV {

    public void escribeCSV(List<Concierto> conciertos, String ruta) {
        PrintWriter out = null;
        FileWriter fichero = null;

        try {

            fichero = new FileWriter(ruta);
            out = new PrintWriter(fichero);

            out.printf("ID Concierto,Fecha,Descripcion,Codigo,Nombre,Num Integrantes,Email,Escenario\n");

           
            for (Concierto c : conciertos) {
                List<Grupo> grupos = c.getGrupo();
                if (grupos == null) {
                    grupos = new ArrayList<>();
                }

                
                for (Grupo g : grupos) {
                    out.printf(
                        Locale.US, 
                        "%s,%s,%s,%s,%s,%s,%s,%s\n",
                        c.getId(),          
                        c.getFecha(),      
                        c.getDescripcion(),    
                        g.getCodigo(),
                        g.getNombre(),     
                        g.getNumIntegrantes(),         
                        g.getEmail(),       
                        c.getEscenario()         
                    );
                }
            }

        } catch (IOException e) {
            
            e.printStackTrace();
        } finally {
            
            if (fichero != null) {
                try {
                    fichero.close();
                    if (out != null) out.close();
                } catch (IOException e) {
                    System.out.println("Error al cerrar el fichero CSV");
                }
            }
        }
    }
}
