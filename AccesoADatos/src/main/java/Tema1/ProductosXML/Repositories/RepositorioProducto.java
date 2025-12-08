package Tema1.ProductosXML.Repositories;

import java.util.List;

import org.apache.logging.log4j.LogManager;

import Tema1.ProductosXML.Models.Producto;

public class RepositorioProducto {

	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(RepositorioProducto.class);

	private List<Producto> productos;

	public RepositorioProducto(List<Producto> productos) {

		super();

		this.productos = productos;

	}

	public List<Producto> getProductos() {

		return productos;

	}

	public void setProductos(List<Producto> productos) {

		this.productos = productos;

	}

}
