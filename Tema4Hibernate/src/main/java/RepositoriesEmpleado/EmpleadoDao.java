package RepositoriesEmpleado;

import ModelsEmpleado.Empleado;
import RepositoriesReunion.AbstractDao;

public class EmpleadoDao extends AbstractDao<Empleado>{
	public EmpleadoDao() {
		setClase(Empleado.class);
	}

}
