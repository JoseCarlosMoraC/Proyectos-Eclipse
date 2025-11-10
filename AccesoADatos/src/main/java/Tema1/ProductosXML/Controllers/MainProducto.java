package Tema1.ProductosXML.Controllers;

import java.util.List;
import java.util.logging.Logger;

import org.apache.logging.log4j.LogManager;


import Tema1.ProductosXML.Models.Producto;
import Tema1.ProductosXML.Models.XMLDomProductos;
import Tema1.ProductosXML.Services.ServicioProducto;

public class MainProducto {

	private static final org.apache.logging.log4j.Logger logger= LogManager.getLogger(MainProducto.class);



	public static void main(String[] args) {

		XMLDomProductos XMLdomProductos= new XMLDomProductos();

		

		try {

			List<Producto> productos = XMLdomProductos.leerpodructosDesdeXML("Productos.xml");

			ServicioProducto servicio = new ServicioProducto(productos);

			List <Producto> sinstock= servicio.obtieneproductosinferiorstock(5);

			servicio.generaJSONnoenventa(sinstock);

			logger.info(productos);

		} catch (Exception e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

	}

}