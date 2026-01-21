package RepositoriesEmpleado;

import java.util.List;

import ModelsEmpleado.Reunion;
import ModelsEmpleado.Sala;

public class SalaDao  extends AbstractDao<Sala>{
	public SalaDao() {
		setClase(Sala.class);
	}

	@Override
	public void create(Sala t) {
		// TODO Auto-generated method stub
		super.create(t);
	}

	@Override
	public void refresh(Sala t) {
		// TODO Auto-generated method stub
		super.refresh(t);
	}

	@Override
	public Sala mergeaObjeto(Sala t) {
		// TODO Auto-generated method stub
		return super.mergeaObjeto(t);
	}

	@Override
	public Sala get(int id) {
		// TODO Auto-generated method stub
		return super.get(id);
	}

	@Override
	public List<Sala> getAll() {
		// TODO Auto-generated method stub
		return super.getAll();
	}

	@Override
	public void update(Sala t) {
		// TODO Auto-generated method stub
		super.update(t);
	}

	@Override
	public void delete(Sala t) {
		// TODO Auto-generated method stub
		super.delete(t);
	}

	@Override
	public Class<Sala> getClase() {
		// TODO Auto-generated method stub
		return super.getClase();
	}

	@Override
	public void setClase(Class<Sala> clase) {
		// TODO Auto-generated method stub
		super.setClase(clase);
	}

	
}
