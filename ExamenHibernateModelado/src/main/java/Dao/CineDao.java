package Dao;

import Models.Cine;
import Utils.AbstractDao;

public class CineDao extends AbstractDao<Cine>{
	public CineDao() {
		setClase(Cine.class);
	}

}