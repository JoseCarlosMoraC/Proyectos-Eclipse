package Services;

import Modelos.Usuario;
import Repositorios.UsuarioRepository;

public class UsuarioService {
private UsuarioRepository repo;

public UsuarioService() {
	super();
	this.repo = new UsuarioRepository();
}

public void altaUsuario(Usuario u) {
	repo.altaUsuario(u);
}

public UsuarioRepository getRepo() {
	return repo;
}

public void setRepo(UsuarioRepository repo) {
	this.repo = repo;
}


}
