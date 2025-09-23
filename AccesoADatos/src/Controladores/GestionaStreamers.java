package Controladores;

import java.time.LocalDate;

import Modelos.Usuario;
import Services.UsuarioService;

public class GestionaStreamers {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
Usuario u = new Usuario(1,"Calvo","ElCalvoDeVillaverde@gmail.com", LocalDate.now());
UsuarioService usuarioServicio = new UsuarioService();

usuarioServicio.altaUsuario(u);
System.out.println(usuarioServicio.getRepo().getUsuarios());
	}

}
