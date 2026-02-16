package Dao;


import Models.Sala;
import Utils.AbstractDao;

public class SalaDao extends AbstractDao<Sala>{
	public SalaDao() {
		setClase(Sala.class);
	}
}
