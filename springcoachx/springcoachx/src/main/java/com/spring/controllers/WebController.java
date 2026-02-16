package com.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/miweb")

public class WebController {
	@Autowired
	private ProductService productService;

	@RequestMapping("/") 
	public String index(Model model) {
		return "index";
	}

	@PostMapping("/producto")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		Product addedProduct = productService.createProduct(product);
		return new ResponseEntity<>(addedProduct, HttpStatus.CREATED);
	}

	@RequestMapping("/lista")
	public String catalog(Model model) {
		List<Product> productos = productService.findAll();
		model.addAttribute("productos", productos);
		return "lista";
	}
	
    // Método para obtener un producto por ID
    @GetMapping("/producto/{id}")
    public String getProductById(@PathVariable Long id, Model model) {
        Product product = productService.findProductById(id);
    	model.addAttribute("detalleProducto", product);
        return "detalle";
    }
    
	
	@ExceptionHandler(ProductNotFoundException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<Response> handleException(ProductNotFoundException pnfe) 
	{
	        Response response = Response.errorResonse(Response.NOT_FOUND, pnfe.getMessage());
	        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	 }

	@PutMapping("/producto")
	public ResponseEntity<Product> updateProduct(@PathVariable Long id,@RequestBody Product product) {
		Product addedProduct = productService.createProduct(product);
		return new ResponseEntity<>(addedProduct, HttpStatus.CREATED);
	}

}
