package RepositoriesReunion;

import java.util.List;

import org.hibernate.Session;

import ModelsReunion.Reunion;
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

}
