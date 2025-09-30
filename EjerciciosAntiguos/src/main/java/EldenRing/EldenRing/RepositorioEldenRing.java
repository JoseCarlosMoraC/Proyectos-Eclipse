package EldenRing.EldenRing;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

// Esta clase es la encargada de almacenar y gestionar todos los Sinluz
public class RepositorioEldenRing {

    // Uso TreeMap con clave String para que los Sinluz estén ordenados alfabéticamente por nombre
    private Map<String, Sinluz> registro;

    public RepositorioEldenRing() {
        this.registro = new TreeMap<>();
    }

    public Map<String, Sinluz> getRegistro() {
        return registro;
    }

    // Devuelve un Sinluz a partir de su id, si no existe lanza una excepción personalizada
    public Sinluz getSinLuz(int id) throws EldenException {
    	boolean encontrado = false; //creo un boolean de encontrado que tenga por defecto falso
    	Sinluz s = null; // creamos un objeto que tenga por defecto null;
        // Uso un iterador para recorrer todos los valores del TreeMap 
        Iterator<Sinluz> it = registro.values().iterator();

        while (it.hasNext() && !encontrado) {
        	
            Sinluz sinluz = it.next();  // obtengo el siguiente Sinluz

          
            if (sinluz.getId() == id) {
            	encontrado = true; // si lo encuentra, lo convertimos en true
            	s = sinluz; // igualamos s que es el objeto con lo que nos pasan
                
            }
         
        }
        if(!encontrado) // si es distinto de encontrado, le lanzamos la excepcion
        	  throw new EldenException("No existe el SinLuz con el id: " + id);
        return s;
     
      
    }

    // Agrega un encuentro a un Sinluz si existe, si no lanza excepción
    public void agregaEncuentro(Encuentro encuentro, int idSinLuz) throws EldenException {
    	
        // Primero busco el Sinluz al que quiero añadir el encuentro
        Sinluz sinluz = getSinLuz(idSinLuz);  // esto lanza una excepcion ya que estoy llamando a otro método

        // Llamo al método de la clase Sinluz para añadir el encuentro a su conjunto
        sinluz.agregarEncuentro(encuentro);
    }
    // Muestra todos los encuentros cuya dificultad es mayor que el valor que pide
    public void mostrarEncuentrosPorDificultad(int dificultad) {
    	
        // Recorro todos los Sinluz del registro
        Iterator<Sinluz> itSinluz = registro.values().iterator();

        while (itSinluz.hasNext()) {
            Sinluz sinluz = itSinluz.next();
            sinluz.tieneEncuentro(dificultad);

            
            }
        }
    }

