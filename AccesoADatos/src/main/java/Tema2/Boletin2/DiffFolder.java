package Tema2.Boletin2;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public class DiffFolder implements Comparable<DiffFolder>{
	private File folder1 = new File("C:\\Users\\alumno\\Desktop\\Folder1");
	private File folder2 = new File("C:\\Users\\alumno\\Desktop\\Folder2");
	
	Set<ResultadoComparacion> comparacion;

	public DiffFolder(File folder1, File folder2, Set<ResultadoComparacion> comparacion) {
		super();
		folder1 = folder1;
		folder2 = folder2;
		this.comparacion = new HashSet<ResultadoComparacion>();
		
		
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



	@Override
	public int compareTo(DiffFolder o) {
	   
	    int result = this.folder1.getName().compareTo(o.folder1.getName());
	    if (result != 0) {
	        return result;
	    }
	    
	    
	    
	    return Long.compare(this.folder1.lastModified(), o.folder1.lastModified());
	}

	
	
}