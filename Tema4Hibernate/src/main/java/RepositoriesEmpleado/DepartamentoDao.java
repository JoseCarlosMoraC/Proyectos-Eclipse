package RepositoriesEmpleado;

import java.util.List;

import ModelsEmpleado.Departamento;
import ModelsReunion.Reunion;
import RepositoriesReunion.AbstractDao;

public class DepartamentoDao extends AbstractDao<Departamento>{
	public DepartamentoDao() {
		setClase(Departamento.class);
	}

	@Override
	public List<Departamento> getAll() {
		// TODO Auto-generated method stub
		return super.getAll();
	}

	@Override
	public void create(Departamento t) {
		// TODO Auto-generated method stub
		super.create(t);
	}

	@Override
	public void refresh(Departamento t) {
		// TODO Auto-generated method stub
		super.refresh(t);
	}

	@Override
	public Departamento mergeaObjeto(Departamento t) {
		// TODO Auto-generated method stub
		return super.mergeaObjeto(t);
	}

	@Override
	public Departamento get(int id) {
		// TODO Auto-generated method stub
		return super.get(id);
	}

	@Override
	public void update(Departamento t) {
		// TODO Auto-generated method stub
		super.update(t);
	}

	@Override
	public void delete(Departamento t) {
		// TODO Auto-generated method stub
		super.delete(t);
	}

	@Override
	public Class<Departamento> getClase() {
		// TODO Auto-generated method stub
		return super.getClase();
	}

	@Override
	public void setClase(Class<Departamento> clase) {
		// TODO Auto-generated method stub
		super.setClase(clase);
	}
	
}
