package RepositoriesEmpleado;

import java.util.List;

import org.hibernate.Session;

import ModelsEmpleado.Reunion;
import Utils.HibernateUtil;
import jakarta.persistence.TypedQuery;

public class ReunionDao extends AbstractDao<Reunion>{
	public ReunionDao() {
		setClase(Reunion.class);
	}

	@Override
	public List<Reunion> getAll() {
		// TODO Auto-generated method stub
		return super.getAll();
	}

	@Override
	public void create(Reunion t) {
		// TODO Auto-generated method stub
		super.create(t);
	}

	@Override
	public void refresh(Reunion t) {
		// TODO Auto-generated method stub
		super.refresh(t);
	}

	@Override
	public Reunion mergeaObjeto(Reunion t) {
		// TODO Auto-generated method stub
		return super.mergeaObjeto(t);
	}

	@Override
	public Reunion get(int id) {
		// TODO Auto-generated method stub
		return super.get(id);
	}

	@Override
	public void update(Reunion t) {
		// TODO Auto-generated method stub
		super.update(t);
	}

	@Override
	public void delete(Reunion t) {
		// TODO Auto-generated method stub
		super.delete(t);
	}

	@Override
	public Class<Reunion> getClase() {
		// TODO Auto-generated method stub
		return super.getClase();
	}

	@Override
	public void setClase(Class<Reunion> clase) {
		// TODO Auto-generated method stub
		super.setClase(clase);
	}

}
