package Tema1.Boletin2;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class DiffFolder {
	private File folder1 = new File("C:\\Users\\alumno\\Desktop\\Folder1");
	private File folder2 = new File("C:\\Users\\alumno\\Desktop\\Folder2");
	
	Set<ResultadoComparacion> comparacion;

	public DiffFolder(File folder1, File folder2, Set<ResultadoComparacion> comparacion) {
		super();
		folder1 = folder1;
		folder2 = folder2;
		this.comparacion = new HashSet<ResultadoComparacion>();
		
		
	}

    public void setFolders(File folder1, File folder2) throws DiffFolderException {
        if (!folder1.exists() || !folder1.isDirectory()) {
            throw new DiffFolderException("El primer archivo no es un directorio válido");
        }

        if (!folder2.exists() || !folder2.isDirectory()) {
            throw new DiffFolderException("El segundo archivo no es un directorio válido");
        }

        this.folder1 = folder1;
        this.folder2 = folder2;
    }
	public File getFolder1() {
		return folder1;
	}

	public void setFolder1(File folder1) {
		folder1 = folder1;
	}

	public File getFolder2() {
		return folder2;
	}

	public void setFolder2(File folder2) {
		folder2 = folder2;
	}

	public Set<ResultadoComparacion> getComparacion() {
		return comparacion;
	}

	public void setComparacion(Set<ResultadoComparacion> comparacion) {
		this.comparacion = comparacion;
	}




	public void compara() {
	   //Metodo mira quien tiene mas ficheros de folder 1 y folder 2(arriba:D)
		//llamar a metodo que es comparaListaFicheros y recibe File1 [], File2 [], isPrimero
		//devuelve un set de resultados, se lo añado a variable(addAll) comparacion
		//llamo a comparaListaFicheros, lo del segundo pero al reves, , !isPrimero
		//log comparacion
	   
	    
	    
	   
	}
	private Set<ResultadoComparacion> comparaListaFicheros(File [] ficheros1, File [] ficheros2, boolean isPrimero){
		//Recorro fichero1, para cada fichero busco ficheros2
		//si está llamo metodo comparaFichero que recibe fichero1, fichero2
		//si no está:
			// Si  isPrimero 
						//añado creo objeto resultado (nombre fichero, FALTA_EN_1)
			//otras
						//añado creo objeto resultado (nombre fichero, FALTA_EN_2)
		return comparacion;
		
	}
	
	//ResultadoComparacion comparaFichero(File fichero1, File fichero2)
	{
		// Si la fecha de fichero 1 es antes que la fichero2:
		//
		//otras
		
	}

	
	
}