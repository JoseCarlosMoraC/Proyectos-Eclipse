package Repositorios;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import EldenRing.EldenRing.Sinluz;
import Modelos.Usuario;

public class UsuarioRepository {

private Set<Usuario> usuarios;

public Set<Usuario> getUsuarios() {
	return usuarios;
}

public void setUsuarios(Set<Usuario> usuarios) {
	this.usuarios = usuarios;
}

public UsuarioRepository() {
	super();
	this.usuarios = new HashSet <Usuario>();
}

public void altaUsuario(Usuario usuario) {
	if(usuarios.contains(usuario)) {
		System.out.println("El usuario ya existe");
	}else {
		usuarios.add(usuario);
	}
}
public void buscarUsuario(int id) {
	boolean encontrado = false;
	Usuario usuario = null;
	Iterator<Usuario> it = usuarios.iterator();
	while (it.hasNext() && !encontrado) {
		Usuario u = it.next();
		if(u.getId() == id) {
			encontrado = true;
			usuario = u;
			
		}
		if(!encontrado) {
			System.out.println("No se encuentra el usuario");
		}
}
}

    }

