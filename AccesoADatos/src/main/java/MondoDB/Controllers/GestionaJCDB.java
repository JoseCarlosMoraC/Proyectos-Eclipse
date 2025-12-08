package MondoDB.Controllers;

import com.mongodb.client.MongoDatabase;

import MondoDB.Config.MongoDBConexion;

public class GestionaJCDB {
	public static void main(String[] args) {
		MongoDBConexion conexion = new MongoDBConexion();
		MongoDatabase db= conexion.getDb();	
//TODO Aquí creamos los diferentes servicios a partir del objeto db	
	}
}
