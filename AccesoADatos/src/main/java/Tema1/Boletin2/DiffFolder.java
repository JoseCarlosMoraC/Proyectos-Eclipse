package Tema1.Boletin2;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class DiffFolder {
	private File folder1 = new File("C:\\Users\\alumno\\Desktop\\Folder1");
	private File folder2 = new File("C:\\Users\\alumno\\Desktop\\Folder2");
	
	Set<ResultadoComparacion> comparacion;

	public DiffFolder() {
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




public Set<ResultadoComparacion> compare() {
		
		Set<ResultadoComparacion> resultado= new HashSet<ResultadoComparacion> ();
		boolean carpeta1mayor=  carpeta1tienemasficheroscarpeta2 ();
		if(carpeta1mayor)
		{
			resultado.addAll(recorreFicheros(folder1, folder2, carpeta1mayor)) ;
			resultado.addAll(recorreFicheros(folder2, folder1, carpeta1mayor)) ;
		}
		else
		{
			resultado.addAll(recorreFicheros(folder2, folder1, carpeta1mayor)) ;
			resultado.addAll(recorreFicheros(folder1, folder2, carpeta1mayor)) ;
			
		}


		//llama al metodo que es compararlistaficheros y recibe File[1], File2 []
		//Devuelve un set de resultados, se lo añado a variable (AddAll) comparacion
		//llamo a comparaListaFicheros, lo del segundo pero al reves
		//log comparacion

	
		return resultado;

	}
	
	
	
	public Set<ResultadoComparacion> comparame () {
		
		Set<ResultadoComparacion> resultados = new HashSet<ResultadoComparacion> ();
		boolean carpeta1esmayor=  carpeta1tienemasficheroscarpeta2 ();
		
		if(carpeta1esmayor) {
		
			resultados.addAll(recorreFicheros(this.folder1,  this.folder2, carpeta1esmayor));
			resultados.addAll(recorreFicheros(this.folder2,  this.folder1, carpeta1esmayor));
		}
		else
		{
			resultados.addAll(recorreFicheros(this.folder2,this.folder1, carpeta1esmayor));
			resultados.addAll(recorreFicheros(this.folder1,  this.folder2, carpeta1esmayor));
		}
		
		return resultados;

		}

	private boolean carpeta1tienemasficheroscarpeta2() {
		File [] archivoscarpeta1= folder1.listFiles();
		File [] archivoscarpeta2= folder2.listFiles();

		return archivoscarpeta1.length>archivoscarpeta2.length;
		
	}
	
	Set<ResultadoComparacion> recorreFicheros(File directorio1, File directorio2, boolean carpeta1esmayor)
	{
		Set<ResultadoComparacion> resultado = new HashSet<ResultadoComparacion> ();
		for(File fichero :  directorio1.listFiles())
		{
			resultado.add(comparFicheroConCarpeta(fichero, directorio2, carpeta1esmayor));
		}
		
		return resultado;
	}
		
	

	
	public ResultadoComparacion comparFicheroConCarpeta(File fichero, File carpeta2, boolean carpeta1esmayor) {
	   
	    boolean encontrado = false;
	    File[] ficherosDirectorio= carpeta2.listFiles() ;
	    int i = 0;
	    ValorComparacion valorcomparacion= null;
	    
	    while (i <ficherosDirectorio.length && !encontrado)
	    {
	    	File archivoiteradocarpeta2 = ficherosDirectorio[i];
	    	
	    	if (fichero.getName().equals(archivoiteradocarpeta2.getName())) 
	    	{
	    		encontrado = true;
	            if (fichero.lastModified() > archivoiteradocarpeta2.lastModified()) {
	                valorcomparacion = ValorComparacion.MENOS_NUEVO_EN_1;
	            } else if (fichero.lastModified() < archivoiteradocarpeta2.lastModified()) {
	            	valorcomparacion = ValorComparacion.MENOS_NUEVO_EN_2;
	            }
	            else if (fichero.lastModified() == archivoiteradocarpeta2.lastModified()) {
	            	valorcomparacion = ValorComparacion.IGUALES;
	            }
	        }
	        else {		    	
	        	i= i+1;
	        }
	    	
	    }
	
	    if(!encontrado)
	    {
	    	if(carpeta1esmayor)
	    	valorcomparacion = ValorComparacion.FALTA_EN_2;
	    	else
	    		valorcomparacion = ValorComparacion.FALTA_EN_1;
	    		
	    }

	    ResultadoComparacion resultado= new ResultadoComparacion(fichero.getName(), valorcomparacion);
	    return resultado;
	}





}
