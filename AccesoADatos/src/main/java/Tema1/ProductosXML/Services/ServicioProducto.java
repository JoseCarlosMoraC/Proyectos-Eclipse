package Tema1.ProductosXML.Services;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.apache.logging.log4j.LogManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Tema1.ProductosXML.Models.Producto;
import Tema1.ProductosXML.Repositories.RepositorioProducto;

public class ServicioProducto {

	private static final org.apache.logging.log4j.Logger logger= LogManager.getLogger(ServicioProducto.class);



	RepositorioProducto repo ;

	String rutaFichero= "src\\main\\resources\\";

	

	

	

	public ServicioProducto( List<Producto> productos) {

		super();

		this.repo = new RepositorioProducto(productos);

	}



	public List<Producto> obtieneproductosinferiorstock ( int numerostock ) throws Exception {



		List<Producto> listaproductosinferiorstock= new ArrayList<>();

		List<Producto> listaproductos = repo.getProductos();

		

		for (Producto producto : listaproductos) {

			if(producto.getStock()<numerostock) {

				listaproductosinferiorstock.add(producto);

			}

		}

		

		

		return listaproductosinferiorstock;

		

		

		

	}

	

	public void retiraDeVentaProductos ( List<Producto> listaproductos ){

		

		for (Producto producto : listaproductos) {

			if(producto.getStock()<5) {

				producto.setEnVenta(false);

			}

		}

		

		

	}

	

	public List<Producto> obtieneproductosstockmenor5 (List<Producto> listaproductos) {

		List<Producto> listaproductosinferiora5 = new ArrayList<>();



		for (Producto producto : listaproductos) {

			if(producto.getStock()<5) {

				listaproductosinferiora5.add(producto);

			}

		}

		return listaproductosinferiora5;

		

		

	}

	public void generaJSONnoenventa(List<Producto> p) throws Exception {



	    String rutaSalida = rutaFichero + "productosNOenVenta.json";

		

	    Gson gson = new GsonBuilder().setPrettyPrinting().create(); 

	    String rutasalida= rutaFichero + "productosNOenventa.json";

	    

	    try (FileWriter writer = new FileWriter(rutaSalida)) {

	        gson.toJson(p, writer);

	        logger.info("CREADO");

	       

	    } catch (IOException e) {

	        logger.info("NO CREADO");

	    }



	}

	//public void escribeEmpleadosEnXML(String nombreFichero, List<Producto> empleados) {

}
